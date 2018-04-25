package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.anDto.BindPhoneDto;
import com.commons.dto.anDto.LoginDTO;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.UserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.UserService;
import com.commons.utils.CommonUtils;
import com.commons.utils.MD5Utils;
import com.dubbo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
  * @Description(功能描述): 用户
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/19 10:59
  **/
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	/**
	  * @Description(功能描述): 新增用户
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/20 16:49
	  **/
	public int addUser(UserEntity user){
		return userMapper.insert(user);
	}

	/**
	  * @Description(功能描述): 用户用户对象查询用户信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 10:35
	  **/
	public UserEntity getUser(UserEntity user){
		return userMapper.selectOne(user);
	}

	/**
	  * @Description(功能描述): 保存用户信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/25 9:41
	  **/
	public int saveUser(UserEntity user) {
		ParamDto dto=new ParamDto();
		dto.put("mobPhone",user.getMobPhone());
		UserEntity isExtis=userMapper.selectUser(dto);						//判断用户手机号码是否存在
		if(CommonUtils.isEmpty(isExtis)){
			if(userMapper.insert(user)<0){
				throw new ScException(AppServiceEnums.SYS_EXCEPTION);		//系统异常
			}
		}else{
			throw new ScException(AppServiceEnums.USER_EXIST);
		}
		return 1;
	}

	/**
	 * @Description(功能描述): 找回密码
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 20:02:21
	 **/
	public int updateUserByKey(UserEntity userEntity) {
		String u=userEntity.getMobPhone();
		UserEntity entity = userMapper.selectPhone(userEntity.getMobPhone());
		if(CommonUtils.isEmpty(entity)){
			throw new ScException(AppServiceEnums.PHONE_NOT_EXIST);
		}
		userEntity.setPwd(MD5Utils.md5(userEntity.getPwd()));
		return userMapper.updatePwd(userEntity.getMobPhone(),userEntity.getPwd());
	}

	/**
	 * @Description(功能描述): 绑定手机号码
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 14:47:43
	 **/
	public BindPhoneDto bindPhone(UserEntity entity) {
		UserEntity user= userMapper.selectPhone(entity.getMobPhone());
		//没有注册过
		if (CommonUtils.isEmpty(user)){
			if (entity.getToken().equals("Q")){
				int qi= userMapper.updateqqPhone(entity.getMobPhone(),entity.getQopenId());
				if (qi<0){
					return buildBindPhoneEror();
				}
				return buildBindPhone();
			}else if(entity.getToken().equals("W")){
				int wi= userMapper.updatewxPhone(entity.getMobPhone(),entity.getWopenId());
				if (wi<0){
					return buildBindPhoneEror();
				}
				return buildBindPhone();
			}
		}
		//手机号码注册过
		throw new ScException(AppServiceEnums.PHONE_IN_BIND);
	}

	/**
	 * @Description(功能描述): 登录
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 10:35:44
	 **/
	public LoginDTO login(UserEntity entity) throws ScException {
		if (entity.getToken() == null) {
			throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
		} else {
			//QQ登录
			if (entity.getToken().equals("Q")) {
				if (CommonUtils.isNotEmpty(entity.getQopenId())) {
					UserEntity selectOne = userMapper.selectOne(entity);
					if (CommonUtils.isEmpty(selectOne)) {
						int qq = userMapper.insert(entity);
						if (qq > 0) {
							return bulidLoginDto(entity, "0");
						} else {
							throw new ScException(AppServiceEnums.SYS_EXCEPTION);
						}
					}
					return bulidLoginDto(entity,"1");
				}
				throw new ScException(AppServiceEnums.ERROR);
				//微信登录
			} else if (entity.getToken().equals("W")) {
				if (CommonUtils.isNotEmpty(entity.getWopenId())) {
					UserEntity selectOne = userMapper.selectOne(entity);
					if (CommonUtils.isEmpty(selectOne)) {
						int wx = userMapper.insert(entity);
						if (wx > 0) {
							return bulidLoginDto(entity, "0");
						}
						throw new ScException(AppServiceEnums.SYS_EXCEPTION);
					}
					return bulidLoginDto(entity, "1");
				}
				throw new ScException(AppServiceEnums.ERROR);
				//手机登录
			} else if (entity.getToken().equals("P")) {
				if (CommonUtils.isNotEmpty(entity.getMobPhone()) &&  CommonUtils.isNotEmpty(entity.getPwd())) {
					entity.setPwd(MD5Utils.md5(entity.getPwd()));
					UserEntity userEntity = userMapper.selectOne(entity);
					if (CommonUtils.isEmpty(userEntity)) {
						throw new ScException(AppServiceEnums.ERROR);
					} else {
						return bulidLoginDtoPone(entity);
					}
				}
				throw new ScException(AppServiceEnums.ERROR);
			}
		}
		return bulidLoginError();
	}
	/**
	 * @Description(功能描述): 对象构建
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-19 9:48:32
	 **/
	public LoginDTO bulidLoginDto(UserEntity entity,String i) {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setImage_url(entity.getImageUrl());
		loginDTO.setName(entity.getName());
		loginDTO.setSex(entity.getSex());
		loginDTO.setToken(i);//1:非首次登录,0:首次登录
		return loginDTO;
	}
	/**
	 * @Description(功能描述): 手机登陆返回对象
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-21 9:48:32
	 **/
	public LoginDTO bulidLoginDtoPone(UserEntity entity) {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setMobphone(entity.getMobPhone());
		return loginDTO;
	}
	/**
	 * @Description(功能描述): 登录错误
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 11:58:32
	 **/
	public LoginDTO bulidLoginError(){
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setStatus("error:失败");
		return loginDTO;
	}

	/**
	 * @Description(功能描述): 手机号码绑定对象返回
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-20 22:26:37
	 **/
	public BindPhoneDto buildBindPhone(){
		BindPhoneDto bindPhoneDto = new BindPhoneDto();
		bindPhoneDto.setFtag("0");// 1:需要选择小区 0:已经选择
		bindPhoneDto.setToken("1");//1:绑定成功 0:绑定失败
		return bindPhoneDto;
	}

	/**
	 * @Description(功能描述): 手机号码绑定对象返回
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-20 22:26:37
	 **/
	public BindPhoneDto buildBindPhone1(){
		BindPhoneDto bindPhoneDto = new BindPhoneDto();
		bindPhoneDto.setFtag("1");
		bindPhoneDto.setToken("1");
		return bindPhoneDto;
	}

	/**
	 * @Description(功能描述): 手机号码绑定对象返回
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-20 22:26:37
	 **/
	public BindPhoneDto buildBindPhoneEror(){
		BindPhoneDto bindPhoneDto = new BindPhoneDto();
		bindPhoneDto.setFtag("1");
		bindPhoneDto.setToken("0");
		return bindPhoneDto;
	}

	/**
	 * @Description(功能描述): 构建根据是手机号码绑定QQ
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 17:01
	 **/
	public UserEntity bulidBindqq(UserEntity entity){
		UserEntity updateEntity = new UserEntity();
		updateEntity.setId(entity.getId());
		updateEntity.setQopenId(entity.getQopenId());
		return updateEntity;
	}

	/**
	 * @Description(功能描述): 构建根据是手机号码绑定微信
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 17:01
	 **/
	public UserEntity bulidBindwx(UserEntity entity){
		UserEntity updateEntity = new UserEntity();
		updateEntity.setId(entity.getId());
		updateEntity.setWopenId(entity.getWopenId());
		return updateEntity;
	}

}

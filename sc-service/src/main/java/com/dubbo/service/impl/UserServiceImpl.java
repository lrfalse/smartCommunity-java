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
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;



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
		dto.put("mobPhone_where",user.getMobPhone());
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
	  * @Description(功能描述): 分页查询 test用
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/26 20:17
	  **/
	public Page<UserEntity> getPageUser(UserEntity user){
		ParamDto dto=new ParamDto();
		Page<UserEntity> page=PageHelper.startPage(dto.getPage(), dto.getRows());
		dto.clear();
		dto.put("mobPhone_where",user.getMobPhone());
		userMapper.selectListUser(dto);
		return page;
	}
	/**
	 * @Description(功能描述): 找回密码
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 20:02:21
	 **/
	public int updateUserByKey(UserEntity userEntity) {
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
	public LoginDTO bindPhone(UserEntity entity) {
		UserEntity user = userMapper.selectPhone(entity.getMobPhone());
		//注册过
		if (CommonUtils.isNotEmpty(user)) {
			ParamDto paramDto = new ParamDto();
			paramDto.put("userId", user.getId());
			paramDto.put("imgUrl", entity.getImgUrl());
			paramDto.put("type", "1");
			if (entity.getToken().equals("Q") || entity.getToken().equals("W")) {
				int qi = userMapper.updateUser(bulidParamDto(entity));
				if (qi < 0) {
					throw new ScException(AppServiceEnums.SYS_EXCEPTION);
				}
				ParamDto paramImgDto = new ParamDto();
				paramImgDto.put("id", user.getId());
				String s = userMapper.queryUserImgId(paramImgDto);
				if (s == null) {
					int qimg = userMapper.addUserImgUrl(paramDto);
					if (qimg < 0) {
						throw new ScException(AppServiceEnums.SYS_EXCEPTION);
					}
					return bulidLoginDtoPone(user);
				}
			}
			//没有注册过
		} else {
			if (entity.getSex().equals("男")) {
				entity.setSex("0");
			} else {
				entity.setSex("1");
			}
			int insert = userMapper.adduser(entity);
			if (insert > 0) {
				ParamDto param = new ParamDto();
				param.put("userId", entity.getId());
				param.put("imgUrl", entity.getImgUrl());
				param.put("type", "1");
				ParamDto paramImgDto = new ParamDto();
				paramImgDto.put("id", entity.getId());
				String s = userMapper.queryUserImgId(paramImgDto);
				if (s == null) {
					int imgUrl = userMapper.addUserImgUrl(param);
					if (imgUrl > 0) {
						return bulidLoginDtoPone(entity);
					}
				}
			}
			throw new ScException(AppServiceEnums.SYS_EXCEPTION);
		}
		throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
	}

	/**
	 * @Description(功能描述): 登录
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 10:35:44
	 **/
	public LoginDTO logIn(UserEntity entity) throws ScException {
		if (entity.getToken() == null) {
			throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
		} else {
			//QQ登录
			if (entity.getToken().equals("Q")) {
				if (CommonUtils.isNotEmpty(entity.getQopenId())) {
					ParamDto dto = new ParamDto();
					dto.put("qopenId_where", entity.getQopenId());
					UserEntity user = userMapper.queryUser(dto);
					if (CommonUtils.isEmpty(user)) {
						return bulidLoginDto(entity);
					}
					LoginDTO loginDTO = bulidLoginDto(user);
					return loginDTO;
				}
				throw new ScException(AppServiceEnums.ERROR);
				//微信登录
			} else if (entity.getToken().equals("W")) {
				if (CommonUtils.isNotEmpty(entity.getWopenId())) {
					ParamDto dto = new ParamDto();
					dto.put("wopenId_where", entity.getWopenId());
					UserEntity user = userMapper.queryUser(dto);
					if (CommonUtils.isEmpty(user)) {
						return bulidLoginDto(entity);
					}
					return bulidLoginDto(user);
				}
				throw new ScException(AppServiceEnums.ERROR);
				//手机登录
			} else if (entity.getToken().equals("P")) {
				if (CommonUtils.isNotEmpty(entity.getMobPhone()) && CommonUtils.isNotEmpty(entity.getPwd())) {
					ParamDto dto = new ParamDto();
					dto.put("pwd_where", MD5Utils.md5(entity.getPwd()));
					dto.put("mobPhone_where", (entity.getMobPhone()));
					UserEntity userEntity = userMapper.selectUser(dto);
					if (CommonUtils.isEmpty(userEntity)) {
						throw new ScException(AppServiceEnums.ERROR);
					} else {
						return bulidLoginDto(userEntity);
					}
				}
				throw new ScException(AppServiceEnums.ERROR);
			}
		}
		return bulidLoginError();
	}

	/**
	 * @Description(功能描述): 绑定手机对象构建
	 * @author(作者): feihong
	 * @date (开发日期):2018/5/4 18:56
	 **/
	public ParamDto bulidParamDto(UserEntity entity) {
		ParamDto paramDto = new ParamDto();
		paramDto.put("name", entity.getName());
		if (entity.getSex().equals("男")) {
			paramDto.put("sex", 0);
		} else {
			paramDto.put("sex", 1);
		}
		if (entity.getToken().equals("Q")) {
			paramDto.put("qopenId", entity.getQopenId());
		} else {
			paramDto.put("wopenId", entity.getWopenId());
		}

		paramDto.put("mobPhone_where", entity.getMobPhone());
		return paramDto;
	}

	/**
	 * @Description(功能描述): 对象构建
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-19 9:48:32
	 **/
	public LoginDTO bulidLoginDto(UserEntity user) {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setImg_url(user.getImgUrl());
		loginDTO.setName(user.getName());
		if (user.getSex().equals("0")) {
			loginDTO.setSex("男");
		} else {
			loginDTO.setSex("女");
		}
		loginDTO.setCommuntiyId(String.valueOf(user.getCommunityId()));
		loginDTO.setMobphone(user.getMobPhone());
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
		loginDTO.setCommuntiyId(String.valueOf(entity.getCommunityId()));
		loginDTO.setImg_url(entity.getImgUrl());
		loginDTO.setName(entity.getName());
		if (entity.getSex().equals("0") | entity.getSex().equals("男")) {
			loginDTO.setSex("男");
		} else {
			loginDTO.setSex("女");
		}

		return loginDTO;
	}

	/**
	 * @Description(功能描述): 登录错误
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 11:58:32
	 **/
	public LoginDTO bulidLoginError() {
		LoginDTO loginDTO = new LoginDTO();
		return loginDTO;
	}

	/**
	 * @Description(功能描述): 手机号码绑定对象返回
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-20 22:26:37
	 **/
	public BindPhoneDto buildBindPhone1() {
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
	public BindPhoneDto buildBindPhoneEror() {
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
	public UserEntity bulidBindqq(UserEntity entity) {
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
	public UserEntity bulidBindwx(UserEntity entity) {
		UserEntity updateEntity = new UserEntity();
		updateEntity.setId(entity.getId());
		updateEntity.setWopenId(entity.getWopenId());
		return updateEntity;
	}

}

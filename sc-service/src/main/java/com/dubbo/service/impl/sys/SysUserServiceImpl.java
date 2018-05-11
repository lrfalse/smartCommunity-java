package com.dubbo.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.SysUserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.sys.SysUserService;
import com.commons.utils.CommonUtils;
import com.commons.utils.DateUtils;
import com.commons.utils.MD5Utils;
import com.dubbo.mapper.sys.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Date;


/**
 * @Description(功能描述): 后台用户
 * @author(作者): lrfalse<wangliyou>
 * @date (开发日期): 2018/4/19 10:59
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
	@Autowired
	private Environment env;
	/**
	  * @Description(功能描述): 根据用户对象获取数据
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/10 9:10
	  **/ 
    public SysUserEntity getSysUser(ParamDto paramDto){
		SysUserEntity sysUser= sysUserMapper.getSysUser(paramDto);
		if(CommonUtils.isNotEmpty(sysUser)){
			String userImgPath=env.getProperty("sc.sys.user.img.url");
			sysUser.setImgUrl(userImgPath+sysUser.getImgUrl());
			return sysUser;
		}
		return null;
	}
	/**
	  * @Description(功能描述): 登录
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/11 10:25
	  **/
	public SysUserEntity login(SysUserEntity sysUser){
		ParamDto paramDto=new ParamDto();
		paramDto.put("name_where", sysUser.getName());
		paramDto.put("status", 0);
		paramDto.put("pwd_where",MD5Utils.md5(sysUser.getPwd()) );
		sysUser= sysUserMapper.login(paramDto);
		if(CommonUtils.isNotEmpty(sysUser)){
			String userImgPath=env.getProperty("sc.sys.user.img.url");
			sysUser.setImgUrl(userImgPath+sysUser.getImgUrl());
			return sysUser;
		}
		return null;
	}

	/**
	  * @Description(功能描述): 保存用户信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/10 15:11
	 * @return int 0:失败，1成功
	  **/
	public int saveSysUser(SysUserEntity sysUser){
		SysUserEntity exist=new SysUserEntity();
		exist.setName(sysUser.getName());
		exist.setMobPhone(sysUser.getMobPhone());
		exist=sysUserMapper.selectOne(exist);
		if(CommonUtils.isEmpty(exist)){
			sysUser.setCreateTime(new Date());
			sysUser.setPwd(MD5Utils.md5(sysUser.getPwd()));
			 return sysUserMapper.insert(sysUser);
		}else{
			throw new ScException(AppServiceEnums.USER_EXIST);
		}
	}

}

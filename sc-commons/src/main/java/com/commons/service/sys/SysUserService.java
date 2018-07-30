package com.commons.service.sys;


import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.SysUserEntity;
import com.github.pagehelper.PageInfo;

/**
  * @Description(功能描述): 后台用户
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/5/10 9:01
  **/
public interface SysUserService {

	/**
	  * @Description(功能描述): 根据条件查询用户信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/10 15:11
	  **/
	SysUserEntity getSysUser(ParamDto paramDto);
	int updateSysUser(SysUserEntity sysUser) ;

	int updateSysUserPwd(SysUserEntity sysUser) ;

	/**
	  * @Description(功能描述): 登录
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/11 10:25
	  **/
	SysUserEntity login(SysUserEntity sysUser);

	int saveSysUser(SysUserEntity sysUser);

	PageInfo findSysUser(ParamDto paramDto);

}

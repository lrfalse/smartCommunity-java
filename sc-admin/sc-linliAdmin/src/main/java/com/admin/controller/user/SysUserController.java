package com.admin.controller.user;

import com.commons.controller.BaseSysApi;
import com.commons.dto.HttpResults;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.SysUserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.enums.SysCodeEnums;
import com.commons.service.RedisService;
import com.commons.service.sys.SysUserService;
import com.commons.utils.CommonUtils;
import com.commons.utils.JsonUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description(功能描述) :用户信息
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/5/10 8:49
 **/
@RestController
public class SysUserController extends BaseSysApi {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private RedisService redisService;

	/**
	  * @Description(功能描述): 后台用户登录
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/10 8:55
	  **/
	@PostMapping("/login")
	public HttpResults login(@RequestBody SysUserEntity sysUser) throws Exception {
		if (CommonUtils.isNotEmpty(sysUser.getPwd()) && CommonUtils.isNotEmpty(sysUser.getName())) {
			sysUser = sysUserService.login(sysUser);
			if (CommonUtils.isNotEmpty(sysUser)) {
				String userJsonStr = JsonUtils.toJson(sysUser);
				redisService.set(sysUser.getMobPhone(), userJsonStr, 30);
				SysUserEntity returninfo =new SysUserEntity();
				returninfo.setName(sysUser.getName());
				returninfo.setMobPhone(sysUser.getMobPhone());
				returninfo.setImgUrl(sysUser.getImgUrl());
				returninfo.setNickName(sysUser.getNickName());
				returninfo.setId(sysUser.getId());
				return getHttpResult(returninfo);
			}
			return getHttpResultError(SysCodeEnums.LOGIN_ERROR);
		}
		return getHttpResultError();
	}
	@PostMapping("/logout")
	public HttpResults logout(@RequestBody SysUserEntity sysUser) throws Exception {
		if (CommonUtils.isNotEmpty(sysUser)) {
			redisService.delete(sysUser.getMobPhone());
			return getHttpResultOk();
		}
		return getHttpResultError();
	}

	/** 
	  * @Description(功能描述): 新增系统用户
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/10 10:43
	  **/ 
	@PostMapping("/addSysUser")
	public HttpResults addSysUser(@RequestBody SysUserEntity sysUser){
		if(CommonUtils.isNotEmpty(sysUser.getPwd(),sysUser.getMobPhone(),sysUser.getNickName())){
			int result=sysUserService.saveSysUser(sysUser);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 修改用户密码
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/21 16:05
	  **/
	@PostMapping("/updateSysUserPwd")
	public HttpResults updateSysUserPwd(@RequestBody SysUserEntity sysUser){
		if(CommonUtils.isNotEmpty(sysUser.getPwd(),sysUser.getId())){
			int result=sysUserService.updateSysUserPwd(sysUser);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}
	@PostMapping("/updateSysUser")
	public HttpResults updateSysUser(@RequestBody SysUserEntity sysUser){
		if(CommonUtils.isNotEmpty(sysUser)){
			int result=sysUserService.updateSysUser(sysUser);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	@PostMapping("/findSysUser")
	public HttpResults findSysUser(@RequestBody(required = false) SysUserEntity sysUser) throws Exception {
		ParamDto dto=new ParamDto();
		if(CommonUtils.isNotEmpty(sysUser)){
			dto.setPage(sysUser.getPage());
			dto.setRows(sysUser.getRows());
		}
		PageInfo result= sysUserService.findSysUser(dto);
		return getHttpResult(result);
	}

}

package com.admin.controller.user;

import com.commons.controller.BaseSysApi;
import com.commons.dto.HttpResults;
import com.commons.entity.sys.SysUserEntity;
import com.commons.service.RedisService;
import com.commons.service.sys.SysUserService;
import com.commons.utils.CommonUtils;
import com.commons.utils.JsonUtils;
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
				redisService.set(sysUser.getName(), userJsonStr, 30);
				SysUserEntity returninfo =new SysUserEntity();
				returninfo.setName(sysUser.getName());
				returninfo.setMobPhone(sysUser.getMobPhone());
				returninfo.setImgUrl(sysUser.getImgUrl());
				returninfo.setNickName(sysUser.getNickName());
				return getHttpResult(returninfo);
			}
			return getHttpResultError();
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

}

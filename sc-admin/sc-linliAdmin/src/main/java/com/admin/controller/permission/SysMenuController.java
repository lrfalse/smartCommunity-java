package com.admin.controller.permission;

import com.commons.controller.BaseSysApi;
import com.commons.dto.HttpResults;
import com.commons.entity.sys.SysUserEntity;
import com.commons.service.RedisService;
import com.commons.service.sys.SysMenuService;
import com.commons.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description(功能描述) :菜单
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/5/10 8:49
 **/
@RestController
public class SysMenuController extends BaseSysApi {

	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private RedisService redisService;

	/**
	  * @Description(功能描述): 根据用户获取菜单权限
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/14 10:24
	  **/
	@PostMapping("/findMenu")
	public HttpResults findMenu(@RequestBody SysUserEntity sysUser) throws Exception {
		if (CommonUtils.isNotEmpty(sysUser.getName())) {
			return getHttpResultError();
		}
		return getHttpResultError();
	}
}

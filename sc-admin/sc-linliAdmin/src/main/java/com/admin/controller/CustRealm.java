package com.admin.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description(功能描述) :
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/5/8 23:09
 **/
public class CustRealm extends AuthorizingRealm {

	Map<String ,String> userMap=new HashMap<>(16);
	{
		userMap.put("wangliyou","123456");
		super.setName("custRealm");
	}
	
	/** 
	  * @Description(功能描述): 授权
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/5/8 23:14
	  **/ 
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String userName= (String) principalCollection.getPrimaryPrincipal();
		Set<String> roles=getRolesByUserName(userName);
		Set<String> permissions=getPermissionsByUserName(userName);
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		authorizationInfo.setStringPermissions(permissions);
		authorizationInfo.setRoles(roles);
		return authorizationInfo;
	}

	/**
	  * @Description(功能描述): 根据用户名获取角色
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/5/8 23:25
	  **/
	private Set<String> getRolesByUserName(String userName) {
		Set<String> sets=new HashSet<>();
		sets.add("admin");
		sets.add("user");
		return sets;
	}

	/**
	  * @Description(功能描述): 获取用户权限
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/5/8 23:25
	  **/
	private Set<String> getPermissionsByUserName(String userName) {
		Set<String> sets=new HashSet<>();
		sets.add("user:detele");
		sets.add("user:update");
		return sets;
	}
	/**
	  * @Description(功能描述): 实现认证
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/5/8 23:14
	  **/
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String username= (String) authenticationToken.getPrincipal();
		String password=getPasswordByUsername(username);
		if(password==null){
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo("wangliyou",password,"custRealm");
		return authenticationInfo;
	}

	/**
	  * @Description(功能描述): 模拟数据库获取密码
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/5/8 23:18
	  **/
	private String getPasswordByUsername(String username) {
		return userMap.get(username);
	}
}

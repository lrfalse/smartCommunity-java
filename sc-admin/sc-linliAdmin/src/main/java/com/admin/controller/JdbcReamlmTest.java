package com.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Description(功能描述) :
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/5/8 22:27
 **/
@SuppressWarnings("Duplicates")
public class JdbcReamlmTest {
	@Test
	public void testAuthentication(){
		//IniRealm iniRealm=new IniRealm("classpath:user.ini");
		JdbcRealm jdbcRealm=new JdbcRealm();
		//构建manage环境
		DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
		defaultSecurityManager.setRealm(jdbcRealm);
		//主体提交认证
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("wangliyou","123456");
		subject.login(usernamePasswordToken);
		System.out.println("isAuthenticated:"+subject.isAuthenticated());
		subject.logout();
		System.out.println("isAuthenticated:"+subject.isAuthenticated());
		//subject.checkRole("admin");//检查角色
		subject.checkRole("user");//检查角色

	}
}

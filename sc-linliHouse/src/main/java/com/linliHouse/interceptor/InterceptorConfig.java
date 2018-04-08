
package com.linliHouse.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
  * @Description(功能描述): 全局拦截器配置
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/2 22:31
  **/
@Configuration
@EnableWebMvc
@ComponentScan
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private Environment env;

	

	/**
	  * @Description(功能描述): 拦截配置
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/2 22:30
	  **/
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/user/login","/user/register","/forgotpwd","/sendsms","/checkvercode","/index","/error","/testHtml");//无需拦截
		super.addInterceptors(registry);
	}

	/**
	  * @Description(功能描述): 拦截模板配置
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/2 22:30
	  **/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
		registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/");
		super.addResourceHandlers(registry);
	}
}

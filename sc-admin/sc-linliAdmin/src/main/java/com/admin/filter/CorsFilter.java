package com.admin.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/** 
  * @Description(功能描述): 请求跨域问题
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/5/11 14:43
  **/ 
@Component
@SuppressWarnings("Duplicates")
public class CorsFilter implements Filter{

	@Autowired
	private Environment env;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    
    /** 
      * @Description(功能描述): 请求域设置
      * @author(作者): lrfalse<wangliyou>
      * @date(开发日期): 2018/5/11 14:21
      **/ 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
		String[] whiteList =env.getProperty("front.end.remote.url").split(",");
		HttpServletRequest request=(HttpServletRequest)servletRequest;
		String myOrigin = request.getHeader("origin");
		boolean isValid = false;
		for( String ip : whiteList ) {
			if( myOrigin != null && myOrigin.equals(ip) ){
				isValid = true;
				break;
			}
		}
		response.setHeader("Access-Control-Allow-Origin", isValid ? myOrigin : "null");
		response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept, X-Requested-With");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}


package com.linli.interceptor;

import com.commons.controller.BaseApi;
import com.commons.dto.IsJsonDTO;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
  * @Description(功能描述): 请求登录拦截
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/2 22:39
  **/
@SuppressWarnings("Duplicates")
public class RequestInterceptor implements HandlerInterceptor {



	/**
	  * @Description(功能描述): 拦截请求配制
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2017/12/26 14:15
	  **/
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		IsJsonDTO isJson=BaseApi.receiveMsg(request);
		if(isJson.isIsjson()){
			request.setAttribute("preHandleJsonDto", isJson);
		}else{
			throw new ScException(AppServiceEnums.SYS_DATA_ERROR);

		}
		return true;
	}

	/**
	  * @Description(功能描述): 处理返回数据
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 16:40
	  **/
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
	}

	public String returnJson(HttpServletResponse response, Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String jsonString = objectMapper.writeValueAsString(object);
			jsonString = jsonString.replace(" ", "");
			response.setContentType("text/json;charset=UTF8");
			response.getWriter().print(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}



package com.personalCenter.handle;

import com.commons.dto.HttpResults;
import com.commons.exception.ScException;
import com.commons.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
  * @Description(功能描述): 全局异常处理器
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/2 22:32
  **/
@ControllerAdvice
public class GlobalExceptionHandler {

	private final  static Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);


	/**
	  * @Description(功能描述): 异常处理
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/2 22:32
	  **/
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public HttpResults handle(Exception e){
		if(e instanceof ScException){
			ScException exception=(ScException)e;
			return ExceptionUtils.retException(exception.getRetCode(),e.getMessage());
		}else{
			logger.error("【系统内部异常】{}",e.getMessage());
			e.printStackTrace();
			return ExceptionUtils.retException();
		}
	}
}


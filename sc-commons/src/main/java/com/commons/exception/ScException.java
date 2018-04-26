package com.commons.exception;


import com.commons.enums.AppServiceEnums;


/**
  * @Description(功能描述): 系统全局异常
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/3/30 14:44
  **/
 public class ScException extends RuntimeException {
    private String retCode;//   状态码

	public ScException() {
	}

	/**
      * @Description(功能描述): 异常的构造方法传入 枚举类信息
      * @author(作者): lrfalse<wangliyou>
      * @date (开发日期): 2018/3/30 14:44
      **/
    public ScException(AppServiceEnums resultEnum){
        super(resultEnum.getMsg());
        this.retCode=resultEnum.getCode();
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }
}

package com.commons.utils;

import com.alibaba.fastjson.JSONObject;
import com.commons.enums.SmsEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description(功能描述) :短信工具(mob三方)
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/17 18:24
 **/
public class SmsUtils {
	private static Logger logger = LoggerFactory.getLogger(SmsUtils.class);
	private static final String checkUrl="https://webapi.sms.mob.com/sms/verify";	//短信验证码验证接口
	private static final String appkey="254975c868b21";								//不允许修改 应用appkey
	private static final String zone="86";											//区号

	/**
	  * @Description(功能描述): 检测验证码是否正确
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/17 18:34
	  * @param code : 短信验证码
	  * @param phone : 电话号码
	  **/
	public static boolean checkCode(String phone,String code){
		try{
			Map<String, String> codeParm=new HashMap<>();
			codeParm.put("appkey",appkey);
			codeParm.put("phone",phone);
			codeParm.put("zone",zone);
			codeParm.put("code",code);
			String result=HttpClientUtil.getInstance().sendHttpPost(checkUrl,codeParm);
			JSONObject jsonObject=JSONObject.parseObject(result);
			System.out.println(jsonObject.toJSONString());
			String status=jsonObject.getString("status");
			if ("200".equals(status) || 200 == Integer.valueOf(status)) {
				return true;
			}
			logger.error("手机号:"+phone+"短信验证码检验失败,返回状态:"+jsonObject.getInteger("status")+",错误原因:"+ SmsEnums.getMsg(status));
		}catch (Exception e){
			return false;
		}
		return false;
	}

	public static void main(String[] args){
	    System.out.println(checkCode("18716393365","4749"));
	}
}

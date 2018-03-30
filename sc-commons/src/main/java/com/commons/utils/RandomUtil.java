package com.commons.utils;

import java.util.Random;

/**
  * @Description(功能描述): 随机数工具类
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/3/30 14:39
  **/
public class RandomUtil {

	/**
	  * @Description(功能描述): 获取固定随机数默认6位
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/3/30 14:39
	  **/
	public static String getRandomNum(){
		return getRandomNum(6);
	}

	/**
	  * @Description(功能描述): 随机数长度
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/3/30 14:39
	  **/
	public static String getRandomNum(int numLength){
		Random random=new Random();
		StringBuilder randomNum=new StringBuilder("");
		for(int i=0;i<numLength;i++){
			randomNum.append(random.nextInt(9));
		}
		return randomNum.toString();
	}
}

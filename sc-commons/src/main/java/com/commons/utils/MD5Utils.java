package com.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description(功能描述) :MD5工具类
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/3 17:57
 **/
public class MD5Utils {

	public static String md5(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(str.getBytes());
			StringBuffer buffer = new StringBuffer();
			for (byte b : result) {
				int number = b & 0xff;				// 加盐
				String md5 = Integer.toHexString(number);
				if (md5.length() == 1) {
					buffer.append("0");
				}
				buffer.append(md5);
			}
			return buffer.toString();			// 标准的md5加密后的结果
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	  * @Description(功能描述): 验证md5值
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 18:27
	  **/
	public static boolean verifyMd5(String md5,String verifyStr){
		if(md5.equalsIgnoreCase(md5(verifyStr))){
			return true;
		}
		return false;
	}
	public static void main(String[] args){
		System.out.println(md5("123456"));

		System.out.println(md5(md5("123456")));
	}
}

/**
 * Copyright (c) 2009 - 2016 LANMEI, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 *LANMEI, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with LANMEI.
 */
package com.commons.utils.encrypt;


import com.commons.utils.CommonUtils;
import com.commons.utils.JsonUtils;
import com.commons.utils.MD5Utils;
import com.thoughtworks.xstream.core.util.Base64Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
  * @Description(功能描述): aes加密解密
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/3 13:49
  **/
public class AESEncryptUtils {
  private static final Logger logger = LoggerFactory.getLogger(AESEncryptUtils.class);
	private static String KEY = "I8jb59qxzrW2H2BEk1lTGmHarkmv8pqE";                 //加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	private static String IV_PARAMETER = "2018040313591229";                      	//偏移量
	public static final String KEY_ALGORITHM = "AES";                       		//密钥算法
	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";  			//加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式
	private static final  String CHSR_SET="UTF-8";                          		//编码格式
	private static AESEncryptUtils instance = null;                         		//实例对象

  public static AESEncryptUtils getInstance() {
      if (CommonUtils.isEmpty(instance))
          instance = new AESEncryptUtils();
      return instance;
  }

  /**
    * @Description(功能描述): aes加密
    * @author(作者): lrfalse<wangliyou>
    * @date (开发日期): 2018/4/3 13:52
    **/
  public static String encrypt(Object data) throws Exception {
      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      byte[] raw = KEY.getBytes();
      SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
      IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
      byte[] encrypted = cipher.doFinal(JsonUtils.toJson(data).getBytes(CHSR_SET));
      return new Base64Encoder().encode(encrypted);
  }

  /**
    * @Description(功能描述): aes解密
    * @author(作者): lrfalse<wangliyou>
    * @date (开发日期): 2018/4/3 13:54
    **/
  public static String decrypt(Object data) throws Exception {
      try {
          byte[] raw = KEY.getBytes(CHSR_SET);
          SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
          Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
          IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
          cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
          byte[] encrypted1 = new Base64Encoder().decode(JsonUtils.toJson(data));		// 先用base64解密
          if(!CommonUtils.isNotEmpty(encrypted1)){
              logger.error("--------------加密数据被窜改----------------");
          }
          byte[] original = cipher.doFinal(encrypted1);
          if(!CommonUtils.isNotEmpty(original)){
              logger.error("---------------加密数据被窜改----------------");
          }
          String originalString = new String(original, CHSR_SET);
          return originalString;
      } catch (Exception ex) {
          logger.error("---------------加密数据被窜改----------------");
          return "";
      }
  }

	@SuppressWarnings("Duplicates")
	public static String encrypt(String data) throws Exception {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		byte[] raw = KEY.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
		IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(data.getBytes(CHSR_SET));
		return new Base64Encoder().encode(encrypted);
	}
	@SuppressWarnings("Duplicates")
	public static String decrypt(String data) throws Exception {
		try {
			byte[] raw = KEY.getBytes(CHSR_SET);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new Base64Encoder().decode(data);		// 先用base64解密
			if(!CommonUtils.isNotEmpty(encrypted1)){
			}
			byte[] original = cipher.doFinal(encrypted1);
			if(!CommonUtils.isNotEmpty(original)){
			}
			String originalString = new String(original, CHSR_SET);
			return originalString;
		} catch (Exception ex) {
			return "";
		}
	}

  public static void main(String[] args) throws Exception {
      String cSrc ="{\"a\":\"net_open\",\"m\":\"do\",\"f\":\"smd\",\"app_id\":\"demo001\",\"app_secret\":\"demo001\",\"device_sncode\":\"M1E8HV61GQR8\",\"access_token\":\"215104e6ad7011cb338618023178550a\",\"net_open_key\":\"43qpDnTNrknxkJ6HzmHMDQ==\"}";           // 需要加密的字串
      String enString = AESEncryptUtils.getInstance().encrypt(cSrc);  // 加密
      System.out.println("加密后的字串值：" + enString);
	  String md5Str = MD5Utils.md5(cSrc);  // 加密
      String str="O7MNnw24T8CUwznVcvw+7VD7f1VORAzA+9izepnaWNorgjh2LhyyCE+PO6Amh8p4i9cN4dIcSi6y\\ngqnOpocQx0JapCc0mjowKiVQxXFXDrAK0tfugqjY56pQk5LBzf9djI6DX4ujTIvkM/w0/s7l3tjr\\nH+nzAGUHQKbw3FKJ3EgG/wMjMhlz7gEprn4exv0SrHJcjKV63kzPZ90GS4FJeXc/um0hzAbYerRvJl1c7l0qW8AOzps9SVR4DIj5LIcw+PYCf35Oz8sOtf6DV++Z3vqqmarSDlq9Y2PQeUyuMZACrGHZ6F/lNVwIi3wpx0yqKIACOgg6XOOTzGUPuMWclxvs8VfbBrMVw4HTxbrRN430Zj5Po++ieeZNyzZ85DQtHUkxuly4IC08y9GV4sVc0YGX/5kTtmrbHJi01BxfvSjrIT1YCXAE5/e2CgvTL67hQv8Pdc4JHrMY3JARaQw5fREt8GNCsmWE7goShrycwLtYxAdVjwV1mG4DScpwscP/";
      String replace = str.replace("\\n", "");
      System.out.println("加密后的md5值：" + md5Str);
      String DeString = AESEncryptUtils.getInstance().decrypt("JmhECASN2+WByQbNnkpg43mVUo235xd9Yky6xkGc40sS6SMdfG3F5uqdVbU+COOEcBshMCjB1bv7A+X1qhxfNWwqKJu61cadSklo8fw1DkfPGUmyG3WmBK0QcMd5R8GmtDB+78iEGaaLfDyR6uBaZA==");
      System.out.println("解密后的字串值：" + DeString);
  }
}

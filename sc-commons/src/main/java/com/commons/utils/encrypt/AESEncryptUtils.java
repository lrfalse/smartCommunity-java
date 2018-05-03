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
	private static String KEY = "6L^U5R$0*brB%GG4";                 			//加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
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
	  String cSrc ="{\"communityId\":\"45624624\",\"pages\":\"1\",\"pageSize\":\"3\"}";           // 需要加密的字串
      String enString = AESEncryptUtils.getInstance().encrypt(cSrc);  // 加密
      System.out.println("加密后的字串值：" + enString);
	  String md5Str = MD5Utils.md5(cSrc);  // 加密
      String str="BO5Tkgxyg/VnzZ7FKFBVbDtt3D/3sIqAOCBpnVkTZN2FF9UDoGdSOKrsmkTqiX1OwJr0Y+xOMV/t\nZB2IHdI+fSkNaDh0iPDnkcH8qp6D7de0dPyowL9jpSrgkQp2HyhULuiIXZPOAP2ZZiET/9z6es90\nUQ8gXdEWLw+jeb8T7AdBCXLtrhbhn5hEdzZZs0pEZ4En3yTYCjkDgqG0LRklWXD4twNJHZMIBhvH\nmhYxDlbDraIS3HFjsN9BoTKKEypTM95musUOr++14E/kWEyOwJQ6lwJp/SHdVtZd6qJGMKddIsaL\n1uzkRSp24C5RNHAR9vau+TLDMr4hx+UifqLmIR4kdbTICxtOYDKuIeb9q0m8Fi9RUyoTMCkflZSC\nLrQi5BebfJYNaFZXq5WFO81pMTAAfLnoWc+Q6APprEyn1FoFAfad5gzkavmKzW71Db4A2KVv+ObN\nsI8GDkllDM52eJYAzsFSg6U/aOaHczcWjgpdZgdabyVKO33HgCWfbODmaOaju9hwtIORzC+Anr5G\n86D8XmuNw6ridf+/U4zRB2Z4lMNJIsVo+MR7erRzPBupnyT7uEi2xfdc8QWhW2l6VQCt0xzcul18\nBmtUKMNCmG6/h8e9ZaKICyXyOPeBl2IDMYp1MZrmG1Q+b9pK7MPczAF87jMK93111pw1r2Vh5Tqg\nJRJ++7J5+nUjv6gn+MsUVg6BNZZQtnXNUTInfHlaq3E05TTTX9A3e9SqKBuEfKmbSnTlWsmC4D0e\n4Mm37HD5yLr/qW/L84mKdf4NJxm18vUE4WljYcBDRn/WgLTRrO85qZ5wLtLIhHTrDSxwpKoUeLXP\naUqjU5zDrZ4Sb/Xsmou142xBNIaZ5UE7Yx4hoNhEEI/6kuekqwC+NF+eCFbl89lWDsb92omYK4DX\n2J/emLeY+2clT8nvU1UINk3oOIqS0pg3Y9RXrZNO5xWDgecDy0b7lrTwS4DIlkyl7vMSigWWHneI\nvM0lmYCdJEKZYuib/jQlzI7VUO9eF1KxYXyX4N71/BAan4z7h6yE6DQCvVpwP8w1eOQ03HQbTz7v\nlh5ZjiWINiNkvWCo0mYKB2IQdmgZ6FL5zqEDQlbE6UJaTfd9ns2Npu3UcvR0VMM+QEqiLOOXfwNU\nuilhXRKiijEGxu4CEQnBuAIXPedIiip3lg==";
      String replace = str.replace("\n", "");
      System.out.println("加密后的md5值：" + md5Str);
      String DeString = AESEncryptUtils.getInstance().decrypt(replace);
      System.out.println("解密后的字串值：" + DeString);
  }
}

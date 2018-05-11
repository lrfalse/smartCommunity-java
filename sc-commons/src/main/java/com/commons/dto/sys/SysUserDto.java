package com.commons.dto.sys;

import lombok.Data;

import java.util.Date;

/**
 * @Description(功能描述) :
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/5/11 11:15
 **/
@Data
public class SysUserDto {
	private String name; 		//真实姓名
	private String imgUrl; 		//头像地址
	private String birthday;	//用户生日
	private String sex;			//用户性别
	private String nickName;	//昵称
	private String identityCode;//身份证号码
	private String mobPhone;	//移动电话
	private String address;		//家庭地址
	private String ostCode;		//邮编
	private String email;		//电子邮件
	private Date createTime;	//创建时间
	private String remark;    	//备注
}

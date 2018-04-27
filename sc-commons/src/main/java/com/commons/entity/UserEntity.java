package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Description:用户信息表
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-09 19:19:02
 */
@Data
@Table(name = "t_user")
public class UserEntity extends BaseIdEntity{

    private String name; 		//真实姓名
    private String imageUrl; 	//头像地址
    private String birthday;	//用户生日
    private String sex;			//用户性别
    private String cityId;		//城市
    private String pwd;			//密码
    private String identityCode;//身份证号码
    private String mobPhone;	//移动电话
    private String homePhone;	//家庭电话
    private String address;		//家庭地址
    private String ostCode;		//邮编
    private String qopenId;		//QQ号码
    private String email;		//电子邮件
    private String seqNo;		//排序号
    private String createTime;	//创建时间
	private String wopenId;		//第三方登录唯一id
	private int communityId;	//用户所在小区
	private String status;		//状态 0:正常,1:非正常
	private String remark;    	//备注
	@Transient
	private String token;		//W:微信 Q:qq P:微信登录

}

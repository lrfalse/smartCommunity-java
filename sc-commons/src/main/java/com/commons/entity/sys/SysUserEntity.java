package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description:用户信息表
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-09 19:19:02
 */
@Data
@Table(name = "sys_user")
public class SysUserEntity extends BaseIdEntity{
	@Column(name="name")
    private String name; 		//真实姓名
	@Column(name="imgUrl")
    private String imgUrl; 		//头像地址
	@Column(name="birthday")
    private String birthday;	//用户生日
	@Column(name="sex")
    private String sex;			//用户性别 1男 2女
	@Column(name="nickName")
    private String nickName;	//昵称
	@Column(name="pwd")
    private String pwd;			//密码
	@Column(name="identityCode")
    private String identityCode;//身份证号码
	@Column(name="mobPhone")
    private String mobPhone;	//移动电话
	@Column(name="address")
    private String address;		//家庭地址
	@Column(name="ostCode")
    private String ostCode;		//邮编
	@Column(name="email")
    private String email;		//电子邮件
	@Column(name="createTime")
    private Date createTime;	//创建时间
	@Column(name="isValid")
	private Integer isValid;		//状态 0:正常,1:非正常
	@Column(name="remark")
	private String remark;    	//备注
	@Column(name="type")
    private String type;        //类型：0平台
}

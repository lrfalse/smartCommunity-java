package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

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

    private String name; 		//真实姓名
    private String imgUrl; 		//头像地址
    private String birthday;	//用户生日
    private String sex;			//用户性别
    private String nickName;	//昵称
    private String pwd;			//密码
    private String identityCode;//身份证号码
    private String mobPhone;	//移动电话
    private String address;		//家庭地址
    private String ostCode;		//邮编
    private String email;		//电子邮件
    private Date createTime;	//创建时间
	private Integer status;		//状态 0:正常,1:非正常
	private String remark;    	//备注
    private String type;        //类型：0平台
}

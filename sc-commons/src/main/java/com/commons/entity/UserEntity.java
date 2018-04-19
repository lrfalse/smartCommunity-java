package com.commons.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:用户信息表
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-09 19:19:02
 */
@Data
@Table(name = "t_user")
public class UserEntity extends BaseIdEntity implements Serializable{


    private String name; //真实姓名
    private String imageUrl; //头像地址


    /**
     *用户生日
     */
    private String birthday;

    /**
     *用户性别
     */
    private String sex;

    /**
     *城市
     */
    private String city;
    /**
     *密码
     */
    private String pwd;

    /**
     *身份证号码
     */
    private String identityCode;

    /**
     *移动电话
     */
    private String mobPhone;

    /**
     *家庭电话
     */
    private String homePhone;

    /**
     *家庭地址
     */
    private String address;

    /**
     *邮编
     */
    private String ostCode;

    /**
     *QQ号码
     */
    private String qq;

    /**
     *电子邮件
     */
    private String email;

    /**
     *排序号
     */
    private String seqNo;



    /**
     *创建时间
     */
    private String createTime;



    /**
     *第三方登录唯一id
     */
    private String opend;

    /**
     *用户所在小区
     */
    private int communityId;



    /**
     *状态 0:正常,1:非正常
     */
    private String status;

    /**
     *备注
     */
    private String remark;
}

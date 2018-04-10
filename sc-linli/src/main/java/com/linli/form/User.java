package com.linli.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:用户信息表
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-09 19:19:02
 */
@Data
public class User implements Serializable{

    /**
     *真实姓名
     */
    private String name;

    /**
     *用户代码
     */
    private String code;

    /**
     *用户生日
     */
    private String birthday;

    /**
     *用户性别
     */
    private String sex;

    /**
     *密码
     */
    private String pwd;

    /**
     *身份证号码
     */
    private String identity_code;

    /**
     *移动电话
     */
    private String mob_phone;

    /**
     *家庭电话
     */
    private String home_phone;

    /**
     *家庭地址
     */
    private String address;

    /**
     *邮编
     */
    private String ost_code;

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
    private String seq_no;

    /**
     *创建人
     */
    private String create_id;

    /**
     *创建时间
     */
    private String create_time;

    /**
     *生效时间
     */
    private String effect_start_date;

    /**
     *失效时间
     */
    private String effect_end_date;

    /**
     *是否可用
     */
    private String disabled;

    /**
     *状态 0:正常,1:非正常
     */
    private String status;

    /**
     *备注
     */
    private String remark;
}

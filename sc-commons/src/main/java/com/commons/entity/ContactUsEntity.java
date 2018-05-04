package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Description(功能描述) : 联系我们表
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 10:23
 */
@Data
@Table( name = "t_contact_us")
public class ContactUsEntity extends BaseIdEntity{
    private String phone;           //电话
    private String address;		    //地址
    private String officialWebsite;	//官网
    private String eMail;	        //电子邮箱
}

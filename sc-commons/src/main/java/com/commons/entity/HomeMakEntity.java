package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "t_homemaking_apply")
public class HomeMakEntity extends BaseIdEntity{
    private String name; //名称

    private Integer age;//年龄

    private String adress;//地址

    private String mobPhone;//电话号码

    private String applyTime;//申请时间


}
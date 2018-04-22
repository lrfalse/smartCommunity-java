package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:小区信息
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-12 10:10:51
 */
@Data
@Table( name = "t_community_info")
public class CommunityEntity implements Serializable {

    private String name; //小区名字

    private String address;

    private String g_location;

    private String b_location;

    private Integer postcode;

    private Integer pcode;

    private String pname;//重庆市

    private Integer citycode;

    private String cityname;

    private Integer adcode;//区域编码

    private String adname;

    private String entr_location;

    private String photos_url;

    private String status;

    private String remark;

}

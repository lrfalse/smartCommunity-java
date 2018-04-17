package com.tools.from;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:小区信息
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-12 10:10:51
 */
@Data
public class T_community_info implements Serializable {

    private String name;

    private String address;

    private String g_location;

    private String b_location;

    private Integer postcode;

    private Integer pcode;

    private String pname;

    private Integer citycode;

    private String cityname;

    private Integer adcode;

    private String adname;

    private String entr_location;

    private String photos_url;

    private String status;

    private String remark;

}

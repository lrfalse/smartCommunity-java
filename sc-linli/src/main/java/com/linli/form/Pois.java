package com.linli.form;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:数据导入 3
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-12 09:9:47
 */
@Data
public class Pois implements Serializable {

    private String id;

    private String name;

    private List<?> tag;

    private String type;

    private String typecode;

    private List<?> biz_type;

    private String address;

    private String location;

    private String tel;

    private List<?> postcode;

    private List<?> website;

    private List<?> email;

    private String pcode;

    private String pname;

    private String citycode;

    private String cityname;

    private String adcode;

    private String adname;

    private List<?> importance;

    private List<?> shopid;

    private String shopinfo;

    private List<?> poiweight;

    private String gridcode;

    private List<?> distance;

    private String navi_poiid;

    private String entr_location;

    private List<?> business_area;

    private List<?> exit_location;

    private String match;

    private String recommend;

    private List<?> timestamp;

    private List<?> alias;

    private String indoor_map;

    private Indoor_data indoor_data;

    private String groupbuy_num;

    private String discount_num;

    private Biz_ext biz_ext;

    private List<?> event;

    private List<Children> children;

    private List<Photos> photos;
}

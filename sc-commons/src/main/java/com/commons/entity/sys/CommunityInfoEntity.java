package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Table;

/**
  * @Description(功能描述): 小区信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 14:51
  **/
@Data
@Table( name = "t_community_info")
public class CommunityInfoEntity extends BaseIdEntity {

    private String name; //小区名字

	private String propertyName;//物业公司名称
	private String propertyId;//物业公司id

    private String address;

    private String g_location;

    private String b_location;

    private Integer postcode;

    private Integer pcode;

    private String pname;//重庆市

    private Integer cityCode;

    private String cityName;

    private Integer adcode;//区域编码

    private String adname;

    private String entr_location;


    private Integer isValid;

    private String remark;

}

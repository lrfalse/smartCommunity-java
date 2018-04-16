package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Description(功能描述) :中国省级行政区表
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/12 9:47
 **/
@Data
@Table( name = "t_district")
public class District extends BaseEntity {
	private Integer adcode;		//主键code(区域code)
	private String name;		//名称
	private String pcode;		//上级code
	private String location;	//'经纬度'
	private String level;		//级别（country：国家、province：省、city：市、district：县、street：街道）

}

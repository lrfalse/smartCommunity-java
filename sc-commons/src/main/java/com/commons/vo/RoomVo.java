package com.commons.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/** 
  * @Description(功能描述): 房号信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/20 15:26
  **/ 
@Data
public class RoomVo implements Serializable {
	private Long id;
	private Long housingEstateId;		//所属小区ID
	private String housingEstateName;	//所属小区名字
	private Long buildingId;			//所属楼栋ID
	private String buildName;			//所属楼栋ID
	private String name;				//房间号
	private Integer isValid;			//是否可用 0 可用
	private String creater;
	private Date createDate;
	private String updater;
	private Date updateDate;
	private String coveredArea;		//建筑面积
	private String usableArea;		//使用面积
	private String unitPrice;		//单价
	private String allPrice;		//总价
	private Long propertyId;		//物业id
	private String propertyName;	//物业id
	private String members;			//住户人数
	private String faces;			//登记人脸数
}
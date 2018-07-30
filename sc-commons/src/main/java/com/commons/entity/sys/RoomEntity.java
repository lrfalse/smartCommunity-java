package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/** 
  * @Description(功能描述): 房号信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/20 15:26
  **/ 
@Data
@Table(name = "room")
public class RoomEntity extends BaseIdEntity {
	@Column(name="housingEstateId")
	private Long housingEstateId;	//所属小区ID
	@Column(name="housingEstateName")
	private String housingEstateName;
	@Column(name="buildingId")
	private Long buildingId;		//所属楼栋ID
	@Column(name="buildName")
	private String buildName;
	@Column(name="name")			//房间号
	private String name;
	@Column(name="isValid")
	private Integer isValid;
	@Column(name="creater")
	private String creater;
	@Column(name="createDate")
	private Date createDate;
	@Column(name="updater")
	private String updater;
	@Column(name="updateDate")
	private Date updateDate;
	@Column(name="coveredArea")
	private String coveredArea;
	@Column(name="usableArea")
	private String usableArea;
	@Column(name="unitPrice")
	private String unitPrice;
	@Column(name="allPrice")
	private String allPrice;
	@Transient
	private Long propertyId;		//物业id
	@Transient
	private String propertyName;		//物业id

	public RoomEntity() {
	}


}
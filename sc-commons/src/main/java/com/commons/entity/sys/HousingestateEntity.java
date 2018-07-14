package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/** 
  * @Description(功能描述): 小区
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 11:58
  **/ 
@Data
@Table(name = "housingestate")
public class HousingestateEntity extends BaseIdEntity {
	@Column(name="provincesId")
	private Long provincesId;		//省份id
	@Column(name="provincesName")
	private String provincesName;	//省份名称
	@Column(name="cityId")
	private Long cityId;			//城市id
	@Column(name="cityName")
	private String cityName;		//城市名称
	@Column(name="areasId")
	private Long areasId;			//区县id
	@Column(name="areasName")
	private String areasName;		//区县名称
	@Column(name="name")
	private String name;			//小区名称
	@Column(name = "propertyId")
	private Long propertyId;		//物业公司id
	@Column(name = "propertyName")
	private String propertyName;	//物业公司名称
	@Column(name = "contacts")
	private String contacts;		//联系人
	@Column(name = "phone")
	private String phone;			//联系电话
	@Transient
	private Long buildings ;		//楼栋数量
	@Column(name="baiLongitude")
	private String baiLongitude;
	@Column(name="baiLatitude")
	private String baiLatitude;
	@Column(name="gorLongitude")
	private String gorLongitude;
	@Column(name="gorLatitude")
	private String gorLatitude;
	@Column(name="account")
	private String account;
	@Column(name="passwords")
	private String passwords;
	@Column(name="userId")
	private String userId;
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




}
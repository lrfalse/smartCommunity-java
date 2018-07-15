package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
  * @Description(功能描述): 楼层信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 19:58
  **/
@Data
@Table(name = "building")
public class BuildingEntity extends BaseIdEntity {
	@Column(name = "propertyId")
	private Long propertyId;	//物业公司id
	@Column(name = "propertyName")
	private Long propertyName;	//物业公司id
	@Column(name = "housingEstateId")
    private Long housingEstateId;
	@Column(name = "housingEstateName")
    private String housingEstateName;
	@Column(name = "name")
    private String name;
	@Column(name = "isValid")
    private Integer isValid;
	@Column(name = "creater")
    private String creater;
	@Column(name = "createDate")
    private Date createDate;
	@Column(name = "updater")
    private String updater;
	@Column(name = "updateDate")
    private Date updateDate;


}
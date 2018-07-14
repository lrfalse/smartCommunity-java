package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

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
	@Column(name = "createdate")
    private Date createdate;
	@Column(name = "updater")
    private String updater;
	@Column(name = "updatedate")
    private Date updatedate;


}
package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "building")
public class BuildingEntity extends BaseIdEntity {
    private Long id;
	private Long propertyId;	//物业公司id
	private Long propertyName;	//物业公司id
    private Long housingEstateId;
    private String housingEstateName;

    private String name;

    private Integer isValid;

    private String creater;

    private Date createdate;

    private String updater;

    private Date updatedate;


}
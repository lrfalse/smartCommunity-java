package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "building")
public class BuildingEntity extends BaseIdEntity {
    private Long id;

    private Long housingestateid;

    private String housingestatename;

    private String name;

    private Integer isvalid;

    private String creater;

    private Date createdate;

    private String updater;

    private Date updatedate;


}
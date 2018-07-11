package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Table;

/** 
  * @Description(功能描述): 区县
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 11:54
  **/
@Data
@Table(name = "areas")
public class AreasEntity extends BaseIdEntity {
    private Long id;

    private Long countrysId;

    private Long provincesId;

    private Long cityId;

    private String code;

    private String name;

    private Integer isValid;


}
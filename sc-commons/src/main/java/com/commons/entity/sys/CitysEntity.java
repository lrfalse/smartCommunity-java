package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Table;

/** 
  * @Description(功能描述): 城市
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 11:54
  **/
@Data
@Table(name = "citys")
public class CitysEntity extends BaseIdEntity {
    private Long id;

    private Long countrysId;

    private Long provincesId;

    private String code;

    private String name;

    private Integer isValid;


}
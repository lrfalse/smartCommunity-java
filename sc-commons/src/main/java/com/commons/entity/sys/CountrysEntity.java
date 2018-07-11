package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Table;

/** 
  * @Description(功能描述): 国家
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 11:57
  **/ 
@Data
@Table(name = "countrys")
public class CountrysEntity extends BaseIdEntity {
    private Long id;

    private String code;

    private String name;

    private Integer isvalid;


}
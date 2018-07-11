package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Table;

/**
  * @Description(功能描述): 省份
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 11:55
  **/
@Data
@Table(name = "provinces")
public class ProvincesEntity  extends BaseIdEntity {
    private Long id;

    private Long countrysId;

    private String code;

    private String name;

    private Integer isValid;


}
package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;
/** 
  * @Description(功能描述): 物业工公司
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 14:51
  **/ 
@Data
@Table(name = "propertycompany")
public class PropertyCompanyEntity extends BaseIdEntity {

    private String propertyName;//物业公司全称

    private String propertyShortname;

    private String name;

    private String phone;

    private Date createDate;

    private String updater;

    private Date updateDate;

    private Integer isValid;

}
package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
/** 
  * @Description(功能描述): 物业公司
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 14:51
  **/ 
@Data
@Table(name = "propertycompany")
public class PropertyCompanyEntity extends BaseIdEntity {
	@Column(name="propertyName")
    private String propertyName;//物业公司全称
	@Column(name="propertyShortname")
    private String propertyShortname;
	@Column(name="name")
    private String name;
	@Column(name="phone")
    private String phone;
	@Column(name="createDate")
    private Date createDate;
	@Column(name="updater")
    private String updater;
	@Column(name="updateDate")
    private Date updateDate;
	@Column(name="isValid")
    private Integer isValid;

}
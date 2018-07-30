package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
  * @Description(功能描述): 中商设备库存表
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/23 17:08
  **/
@Data
@Table(name = "gat_dervice_inventory")
public class GatDerviceInventoryEntity extends BaseIdEntity {
	@Column(name="derviceNum")
    private String derviceNum;	//设备串号
	@Column(name="status")
    private Integer status;		//是否注册（0未注册，1已注册）
	@Column(name="deviceName")
    private String deviceName;	//部署地址名称


}
package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/** 
  * @Description(功能描述): 小区
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 11:58
  **/ 
@Data
@Table(name = "housingestate")
public class HousingestateEntity extends BaseIdEntity {
    private Long id;

    private Long provincesid;	//省份名称

    private String provincesname;//省份名称

    private Long cityid;	//城市id

    private String cityname;	//城市名称

    private Long areasid;	//区县id

    private String areasname;//区县名称

    private String name;	//小区名字

    private String bailongitude;

    private String bailatitude;

    private String gorlongitude;

    private String gorlatitude;

    private String account; //第三方账号

    private String passwords;//第三方密码

    private String userid;//第三方用户id

    private Integer isvalid;//是否可用

    private String creater;

    private Date createdate;

    private String updater;

    private Date updatedate;


}
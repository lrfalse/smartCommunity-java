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

    private Long propertyId;	//物业公司id
    private Long propertyName;	//物业公司名字
    private Long provincesId;	//省份id

    private String provincesName;//省份名称

    private Long cityId;	//城市id

    private String cityName;	//城市名称

    private Long areasId;	//区县id

    private String areasName;//区县名称

    private String name;	//小区名字

    private String baiLongitude;

    private String baiLatitude;

    private String gorLongitude;

    private String gorLatitude;
    private String phone;		//联系电话
    private String contacts;	//	联系人

    private String account; //第三方账号

    private String passwords;//第三方密码

    private String userId;//第三方用户id

    private Integer isValid;//是否可用

    private String creater;

    private Date createDate;

    private String updater;

    private Date updateDate;


}
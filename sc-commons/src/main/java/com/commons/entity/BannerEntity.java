package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Description(功能描述) : 首页轮播图表
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/27 21:56
 */
@Data
@Table( name = "t_banner")
public class BannerEntity extends BaseIdEntity{
    private Date startTime;	    //有效开始时间
    private Date endTime;	    //有效结束时间
    private String linkUrl;		//点击跳转地址
    private String imgUrl;		//图片地址
    private Integer status;	    //状态（0：正常 、1失效）
}

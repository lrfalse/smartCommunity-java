package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Description(功能描述) : 首页轮播图表
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/7 17:08
 */
@Data
@Table(name = "t_banner_img")
public class BannerImgEntity extends BaseIdEntity {
    private Integer banner_id;      //首页轮播id
    private String imgUrl;		    //图片地址
    private Integer type;	        //0:banner轮播图
}

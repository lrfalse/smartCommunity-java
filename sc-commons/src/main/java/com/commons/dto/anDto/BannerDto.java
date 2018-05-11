package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description(功能描述) : 首页轮播图dto
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/7 17:11
 */
@Data
public class BannerDto implements Serializable {
    private Long id;
    private Long communityId;	//小区id
    private Date startTime;	    //有效开始时间
    private Date endTime;	    //有效结束时间
    private String linkUrl;		//点击跳转地址
    private Integer status;	    //状态（0：正常 、1失效）
    private String imgUrl;		//图片地址（多图用，隔开）
}

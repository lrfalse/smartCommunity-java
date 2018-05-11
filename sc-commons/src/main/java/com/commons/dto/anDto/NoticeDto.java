package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description(功能描述) : 小区公告列表dto
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/27 11:31
 */
@Data
public class NoticeDto implements Serializable {
    private Integer id;
    private Integer communityId;//所属小区ID
    private String title;		//公告标题
    private String smallTitle;	//小标题
    private String content;		//公告内容
    private String issuer;		//发布方
    private Integer type;		//公告类型(0：置顶、1加急、2普通)
    private Integer status;		//公告状态（0：正常 、1失效）
    private Integer browseNum=0;//浏览次数
    private Date publishTime;	//发布时间
    private String imgUrl;		//图片地址（多图用，隔开）
    private Integer total;	    //评论次数
}

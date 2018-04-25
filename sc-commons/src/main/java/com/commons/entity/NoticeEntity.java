package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Description(功能描述) : 小区公告表
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/25 11:13
 */
@Data
@Table( name = "t_notice")
public class NoticeEntity extends BaseIdEntity{
    private Integer communityId;	//所属小区ID
    private String titile;		//公告标题
    private String content;		//公告内容
    private String issuer;		//发布方
    private Integer type;		//公告类型(0：加急、1置顶、2普通)
    private Integer status;		//公告状态（0：正常 、1失效）
    private String img_url;		//图片地址（多图用，隔开）
    private Integer browse_num;	//浏览次数
    private Integer comment_num;//评论次数
    private Date publish_time;	//发布时间
    private Date start_time;	//有效开始时间
    private Date end_time;	    //有效结束时间
}
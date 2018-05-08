package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Description(功能描述) : 小区公告评论表
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/26 16:15
 */
@Data
@Table( name = "t_notice_comment")
public class NoticeCommentEntity extends BaseIdEntity{
    private Integer userId;	    //评论人id
    private Integer noticeId;	//公告id
    private String content;		//评论内容
    private Integer status;		//评论状态(0正常，1失效)
    private Date commentTime;	//评论时间
    private String userName;	//用户名称
    private String imgUrl;	    //用户头像图片
}

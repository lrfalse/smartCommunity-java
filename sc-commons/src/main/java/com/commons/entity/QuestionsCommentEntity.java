package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Description(功能描述) : 问答评论表
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/3 11:41
 */
@Data
@Table(name = "t_questions_comment")
public class QuestionsCommentEntity extends BaseIdEntity {
    private Long userId;		    //评论人
    private String userName;		//评论人名称
    private Long questionsId;       //问答id
    private String content;		    //评论内容
    private Integer status;		    //评论状态(0正常，1失效)
    private String imgUrl;		    //图片地址
    private Date commentTime;	    //评论时间
}

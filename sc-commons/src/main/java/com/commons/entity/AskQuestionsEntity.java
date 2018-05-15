package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @Description(功能描述) : 问答表
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 14:32
 */
@Data
@Table(name = "t_questions")
public class AskQuestionsEntity extends BaseIdEntity {
    private Long communityId;   //所属小区ID
    private String title;		//问题标题
    private String content;		//问题内容
    private Long userId;		//发布人ID
    private String type;		//问题类型 (邻里聊天室类型) t_chat_type
    private Integer status;		//问题状态（0：正常 、1失效）
    private Integer browseNum;	//浏览次数
    private Date publishTime;	//发布时间
    @Transient
    private Boolean popular;    //true为热门问题/false为最新问题
}

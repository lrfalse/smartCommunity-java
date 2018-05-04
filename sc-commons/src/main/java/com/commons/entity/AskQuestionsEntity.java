package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Description(功能描述) : 问答表
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 14:32
 */
@Data
@Table(name = "t_questions")
public class AskQuestionsEntity extends BaseIdEntity {
    private Integer communityId;//所属小区ID
    private String title;		//问题标题
    private String content;		//问题内容
    private Integer userId;		//发布人ID
    private String type;		//问题类型（1：美食，2：运动，3：情感，4：育儿，5：教育，6：社区，7：知识，8：娱乐）多个用逗号隔开
    private Integer status;		//问题状态（0：正常 、1失效）
    private Integer browseNum;	//浏览次数
    private Date publishTime;	//发布时间
}

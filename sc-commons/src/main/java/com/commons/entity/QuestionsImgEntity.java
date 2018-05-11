package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Description(功能描述) : 问答图片表
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/4 16:29
 */
@Data
@Table(name = "t_questions_img")
public class QuestionsImgEntity extends BaseIdEntity {
    private Long questionsId;       //问答id
    private String imgUrl;		    //图片地址
    private Integer type;	        //图片类型0：问题图片 1：问题回答图片
}

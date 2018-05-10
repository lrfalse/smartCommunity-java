package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Description(功能描述) : 邻里聊天室类型表
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 14:32
 */
@Data
@Table(name = "t_chat_type")
public class ChatTypeEntity extends BaseIdEntity {
    private String name;		//聊天室名称
    private Integer status;		//状态(0：正常 ，1：失效)
}

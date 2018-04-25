package com.commons.dto.reDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description(功能描述) : 首页小区公告请求dto
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/25 22:43
 */
@Data
public class NoticeReDto implements Serializable{

    private String communityId; //所属小区ID
}

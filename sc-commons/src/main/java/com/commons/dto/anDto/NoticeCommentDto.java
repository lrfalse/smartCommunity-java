package com.commons.dto.anDto;

import lombok.Data;

import java.util.Date;

/**
 * @Description(功能描述) : 小区公告详情评论
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/26 19:24
 */
@Data
public class NoticeCommentDto extends BaseDTO {
    private Integer userId;	    //评论人id
    private Integer noticeId;	//公告id
    private String name;        //真实姓名
    private String imageUrl;    //头像地址
    private String content;		//评论内容
    private Date commentTime;	//评论时间
}

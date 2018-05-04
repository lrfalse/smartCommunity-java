package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description(功能描述) : 我的问题
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 18:07
 */
@Data
public class AskQuestionsDto implements Serializable {
    private Integer id;
    private Integer communityId;//所属小区ID
    private String title;		//问题标题
    private String content;		//问题内容
    private Integer userId;		//发布人ID
    private String type;		//问题类型（1：美食，2：运动，3：情感，4：育儿，5：教育，6：社区，7：知识，8：娱乐）多个用逗号隔开
    private Integer status;		//问题状态（0：正常 、1失效）
    private String imgUrl;		//图片地址（多图用，隔开）
    private Integer browseNum;	//浏览次数
    private Integer total;      //评论总数
    private Date publishTime;	//发布时间
    private Boolean popular;    //true为热门问题/false为最新问题
}

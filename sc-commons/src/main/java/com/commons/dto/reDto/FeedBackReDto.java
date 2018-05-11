package com.commons.dto.reDto;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import java.util.List;

/**
 * @Description:意见回馈
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018/05/03 11:11:41
 */
@Data
public class FeedBackReDto extends BaseIdEntity {

    /**
     *意见反馈内容
     */
    private String context;

    /**
     *用户id
     */
    private String userId;

    /**
     *用户意见反馈提交图片
     */
    private String imgUrl;

    /**
     *反馈时间
     */
    private String feedbackTime;

    /**
     *'状态：0正常 1失效'
     */
    private String status;

    /**
     *图片集
     */
    private List<String> list;
}


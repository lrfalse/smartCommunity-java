package com.commons.dto.reDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description(功能描述) : 请求分页对象
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/28 10:16
 */
@Data
public class BasePageReDto<T> implements Serializable {
    private int pages;   //当前页
    private int pageSize;   //页数大小
    private  String token;  //唯一标识
}

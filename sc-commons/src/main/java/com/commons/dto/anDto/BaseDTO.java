package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description(功能描述) : 基础信息DTO
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/26 20:19
 */
@Data
public class BaseDTO implements Serializable {
    private Integer id;
    private Integer page = 1;
    private Integer rows = 2;
}

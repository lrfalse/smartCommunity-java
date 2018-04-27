package com.commons.dto.anDto;

import com.commons.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @Description(功能描述) : 分页基础信息DTO
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/26 20:19
 */
@Data
public class BasePageDto<T> extends BaseEntity{
    private Long total;
    private List<T> list;
}

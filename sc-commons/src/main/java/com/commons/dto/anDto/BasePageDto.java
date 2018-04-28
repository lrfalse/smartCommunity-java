package com.commons.dto.anDto;

import com.commons.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description(功能描述) : 分页基础信息DTO
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/26 20:19
 */
@Data
public class BasePageDto<T> implements Serializable {
    private int total=0;         //总条数
    private int currentPage=0;   //当前页数
    private List<T> list;        //具体list对象

    public BasePageDto(PageInfo<T> pageInfo){
        if(CommonUtils.isNotEmpty(pageInfo)){
            this.total=(int) pageInfo.getTotal();
            this.currentPage=pageInfo.getPageNum();
            this.list=pageInfo.getList();
        }
    }
}

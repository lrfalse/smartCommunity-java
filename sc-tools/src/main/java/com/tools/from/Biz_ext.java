package com.tools.from;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:数据导入 5
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-12 10:10:14
 */
@Data
public class Biz_ext implements Serializable {

    private List<?> rating;

    private List<?> cost;
}

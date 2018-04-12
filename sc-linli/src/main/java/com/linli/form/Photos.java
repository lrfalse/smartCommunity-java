package com.linli.form;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:数据导入7
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-12 10:10:21
 */
@Data
public class Photos implements Serializable {

    private List<?> title;

    private String url;
}

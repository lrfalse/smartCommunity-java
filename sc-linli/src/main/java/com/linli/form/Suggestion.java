package com.linli.form;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:数据导入2
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-12 09:9:44
 */
@Data
public class Suggestion implements Serializable{

    private List<?> keywords ;

    private List<?> cities;
}

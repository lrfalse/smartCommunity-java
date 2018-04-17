package com.tools.from;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:数据导入
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-12 09:9:42
 */
@Data
public class Demo implements Serializable{

    private String status;

    private String count;

    private String info;

    private String infocode;

    private Suggestion suggestion;

    private List<Pois> pois;

}

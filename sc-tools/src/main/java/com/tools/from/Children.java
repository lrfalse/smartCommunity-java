package com.tools.from;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:数据导入6
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-12 10:10:17
 */
@Data
public class Children implements Serializable {

    private String id;

    private String name;

    private String sname;

    private String location;

    private String address;

    private String distance;

    private String subtype;
}

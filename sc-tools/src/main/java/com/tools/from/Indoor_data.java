package com.tools.from;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:数据导入4
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-12 10:10:10
 */
@Data
public class Indoor_data implements Serializable{

    private List<?> cpid;

    private List<?> floor;

    private List<?> truefloor;

    private List<?> cmsid;

}

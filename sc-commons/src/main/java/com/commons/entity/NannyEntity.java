package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;


@Data
@Table(name = "t_nanny")
public class NannyEntity extends BaseIdEntity{

    private String name;//姓名

    private Integer age;//年龄

    private Long ethnicId;//民族id

    private String ethnic;//民族

    private String education;//学历(0无 、1小学 、2中学 、3高中 、4大学、5博士、6硕士)

    private String trainResult;//培训结果

    private String trainName;//培训名称

    private String trainTime;//培训时间

    private String certificate;//培训证书

    private String billHealth;//健康证书

    private String identityCard;//身份证

    private String mobPhone;//联系电话

    private String address;//地址

    //private String photoUrl;//个人照片（多个，隔开）

    private String communityName;//小区名称

    private Long communityId;//小区名称

    private String workStartTime;//工作开始时间

    private String workEndTime;//工作结束时间

    private Integer workTime;//工作年限

    private Integer servicePnum;//服务人数

    private String type;//类型 保姆，月嫂，育儿嫂，护工

    @Transient
    private String tag;//0 本小区 1//非本小区

}
package com.commons.vo;

import lombok.Data;

import java.io.Serializable;

/**
  * @Description(功能描述): 业主成员表
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/20 15:24
  **/
@Data
public class MemberVo implements Serializable {
	private Long id;
	private String name;			//房间号
	private Integer isValid;		//是否可用 0 可用
	private String phone;			//电话号码
	private String relationship;	//身份 1业主 2家庭成员 3租户
	private String sex;				//性别 1男 2女
	private String age;				//年龄
	private String faces;			//是否登录人脸 等于=为否

}
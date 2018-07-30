package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
  * @Description(功能描述): 业主成员表
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/20 15:24
  **/
@Data
@Table(name = "member")
public class MemberEntity extends BaseIdEntity {
	@Column(name="openId")
	private String openId;
	@Column(name="name")
	private String name;
	@Column(name="phone")
	private String phone;
	@Column(name="sex")
	private Integer sex;		//性别 1男 2女
	@Column(name="relationship")
	private Integer relationship;//身份 1业主 2家庭成员 3租户
	@Column(name="mid")
	private Long mid;
	@Column(name="mbName")
	private String mbName;
	@Column(name="isPerfectIdentity")
	private Integer isPerfectIdentity;
	@Column(name="isFaceRecognition")
	private Integer isFaceRecognition;
	@Column(name="creater")
	private String creater;
	@Column(name="createDate")
	private Date createDate;
	@Column(name="updater")
	private String updater;
	@Column(name="updateDate")
	private Date updateDate;
	@Column(name="age")
	private Integer age;
	@Column(name="isValid")
	private Integer isValid;
	@Transient
	private Long roomId;


}
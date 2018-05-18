package com.commons.entity;

import lombok.Data;

/**
  * @Description(功能描述): 文件上传配制信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/5/17 15:27
  **/
@Data
public class FastDFSFileEntity {
	private String name;	//文件名
	private byte[] content;	//文件内容
	private String ext;		//格式
	private String md5;		//md5匹配
	private String author;	//作者

	private String group;	//分组信息
	private String imgUrl;	//图片路径信息

	public FastDFSFileEntity(String name, byte[] content, String ext, String height,
							 String width, String author) {
		super();
		this.name = name;
		this.content = content;
		this.ext = ext;
		this.author = author;
	}

	public FastDFSFileEntity(String name, byte[] content, String ext) {
		super();
		this.name = name;
		this.content = content;
		this.ext = ext;

	}


}
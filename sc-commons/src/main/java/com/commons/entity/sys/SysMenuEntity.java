package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "sys_menu")
public class SysMenuEntity  extends BaseIdEntity {
    private Long pId;		//父id
    private String name;	//菜单名称
    private String href;	//连接地址
    private String icon;	//图标名称
    private Date cteateTime;//创建时间
    private String createBy;//创建人
    private Integer sort;	//排序
    private Integer status;	//状态 (0：正常 1：失效)
    private String remark;	//备注


}
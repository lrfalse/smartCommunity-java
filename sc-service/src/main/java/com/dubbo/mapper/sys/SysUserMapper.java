package com.dubbo.mapper.sys;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.SysUserEntity;

import java.util.List;

/**
 * @Description(功能描述) :后台用户信息
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/20 16:46
 **/
public interface SysUserMapper extends MyMapper<SysUserEntity> {

    /**
     * @Description(功能描述): 登录
     * @author(作者): lrfalse<wangliyou>
     * @date(开发日期): 2018/5/11 10:23
     **/
    SysUserEntity login(ParamDto paramDto);

    /**
     * @Description(功能描述): 根据where条件查询用户信息
     * @author(作者): lrfalse<wangliyou>
     * @date(开发日期): 2018/5/11 10:58
     **/
    SysUserEntity getSysUser(ParamDto paramDto);


	List<SysUserEntity> findSysUser(ParamDto paramDto);
}
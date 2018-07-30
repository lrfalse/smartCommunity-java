package com.dubbo.mapper.sys;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.CommunityInfoEntity;
import com.commons.entity.sys.HousingestateEntity;

import java.util.List;

/**
  * @Description(功能描述): 小区信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 14:54
  **/
public interface HousingestateMapper extends MyMapper<HousingestateEntity> {


	/**
	  * @Description(功能描述): 查询小区信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 14:53
	  **/
	List<HousingestateEntity> findHousingestate(ParamDto paramDto);

	int  updateHousingestateByParam(ParamDto paramDto);
}

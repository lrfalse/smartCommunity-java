package com.commons.service.sys;

import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.CommunityInfoEntity;
import com.commons.entity.sys.HousingestateEntity;
import com.github.pagehelper.PageInfo;

/**
  * @Description(功能描述): 小区信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 14:59
  **/
public interface HousingestateService {

	int saveHousingestate(HousingestateEntity housingestate);

	int updateHousingestate(HousingestateEntity housingestate);

	PageInfo<HousingestateEntity> findHousingestate(ParamDto paramDto);

}

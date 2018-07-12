package com.dubbo.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.HousingestateEntity;
import com.commons.service.sys.HousingestateService;
import com.dubbo.mapper.sys.HousingestateMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
  * @Description(功能描述): 小区信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 14:54
  **/
@Service
public class HousingestateServiceImpl implements HousingestateService {

    @Autowired
    private HousingestateMapper housingestateMapper;

	public int saveHousingestate(HousingestateEntity housingestate){
		return housingestateMapper.insert(housingestate);
	}

	public int updateHousingestate(HousingestateEntity housingestate){
		return housingestateMapper.updateByPrimaryKey(housingestate);
	}

    /**
      * @Description(功能描述): 查询小区信息
      * @author(作者): lrfalse<wangliyou>
      * @date(开发日期): 2018/7/10 14:54
      **/
    public PageInfo<HousingestateEntity> findHousingestate(ParamDto paramDto) {
        PageHelper.startPage(paramDto.getPage(),paramDto.getRows());
        return new PageInfo<>( housingestateMapper.findHousingestate(paramDto));
    }


}

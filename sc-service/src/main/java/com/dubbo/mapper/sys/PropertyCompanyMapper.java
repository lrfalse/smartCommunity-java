package com.dubbo.mapper.sys;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.PropertyCompanyEntity;

import java.util.List;

/**
  * @Description(功能描述): 添加物业
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/9 17:12
  **/
public interface PropertyCompanyMapper extends MyMapper<PropertyCompanyEntity> {

	List<PropertyCompanyEntity> findPropertyCompanys(ParamDto dto);
}

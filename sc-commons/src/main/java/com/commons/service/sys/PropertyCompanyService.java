package com.commons.service.sys;

import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.PropertyCompanyEntity;
import com.github.pagehelper.PageInfo;

/**
 * @Description:社区活动
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-27 15:15:52
 */
public interface PropertyCompanyService {

	int savePropertyCompany(PropertyCompanyEntity propertyCompany);

	int updatePropertyCompany(PropertyCompanyEntity propertyCompany);

	PageInfo<PropertyCompanyEntity> findPropertyCompanys(ParamDto paramDto);
}

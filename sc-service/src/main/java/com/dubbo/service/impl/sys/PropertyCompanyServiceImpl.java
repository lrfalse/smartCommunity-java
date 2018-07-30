package com.dubbo.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.PropertyCompanyEntity;
import com.commons.service.sys.PropertyCompanyService;
import com.dubbo.mapper.sys.BuildingMapper;
import com.dubbo.mapper.sys.HousingestateMapper;
import com.dubbo.mapper.sys.PropertyCompanyMapper;
import com.dubbo.mapper.sys.RoomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
  * @Description(功能描述): 添加物业
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/9 17:14
  **/
@Service
public class PropertyCompanyServiceImpl implements PropertyCompanyService {
	@Autowired
	private PropertyCompanyMapper propertyCompanyMapper;
	@Autowired
	private HousingestateMapper housingestateMapper;
	@Autowired
	private BuildingMapper buildingMapper;
	@Autowired
	private RoomMapper roomMapper;

	/**
	 * @Description(功能描述): 修改物业信息
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/9 21:12
	 **/
	public int updatePropertyCompany(PropertyCompanyEntity propertyCompany){
		int result= propertyCompanyMapper.updateByPrimaryKey(propertyCompany);
		ParamDto paramDto=new ParamDto();
		if(result>0){
			paramDto.put("propertyName", propertyCompany.getPropertyName());
			paramDto.put("propertyId_where", propertyCompany.getId());
		}
		housingestateMapper.updateHousingestateByParam(paramDto);//修改小区表的物业信息
		buildingMapper.updateBuildingByParam(paramDto);			 //修改楼道表的物业信息
		roomMapper.updateRoomByParam(paramDto);					 //修改房号表的物业信息
		return result;
	}

	/**
	  * @Description(功能描述): 保存
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/9 17:17
	  **/
	public int savePropertyCompany(PropertyCompanyEntity propertyCompany){
		propertyCompany.setCreateDate(new Date());
		return propertyCompanyMapper.insert(propertyCompany);
	}


	/**
	  * @Description(功能描述): 查询物业公司
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/9 18:26
	  **/
	public PageInfo<PropertyCompanyEntity> findPropertyCompanys(ParamDto dto){
		PageHelper.startPage(dto.getPage(),dto.getRows(),"createDate desc");
		List<PropertyCompanyEntity> PropertyCompanys = propertyCompanyMapper.findPropertyCompanys(dto);
		return new PageInfo<>(PropertyCompanys);
	}


}
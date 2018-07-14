package com.admin.controller.Housingestate;

import com.commons.controller.BaseSysApi;
import com.commons.dto.HttpResults;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.PropertyCompanyEntity;
import com.commons.enums.SysCodeEnums;
import com.commons.service.sys.PropertyCompanyService;
import com.commons.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description(功能描述) :物业信息
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/7/9 17:19
 **/
@RestController
public class PropertyCompanyController extends BaseSysApi {

	@Autowired
	private PropertyCompanyService propertyCompanyService;
	/**
	  * @Description(功能描述): 添加物业信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/9 17:20
	  **/
	@PostMapping("/saveProperty")
	public HttpResults saveProperty(@RequestBody PropertyCompanyEntity propertyCompany){
		if(CommonUtils.isNotEmpty(propertyCompany)){
			ParamDto dto=new ParamDto();
			dto.put("propertyName_where", propertyCompany.getPropertyName());
			PageInfo counts= propertyCompanyService.findPropertyCompanys(dto);
			if(counts.getTotal()>0){
				return getHttpResultError(SysCodeEnums.PROPERCOMPANY_EXIST);
			}
			int result=propertyCompanyService.savePropertyCompany(propertyCompany);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 修改物业信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/9 21:12
	  **/
	@PostMapping("/updateProperty")
	public HttpResults updateProperty(@RequestBody PropertyCompanyEntity propertyCompany){
		if(CommonUtils.isNotEmpty(propertyCompany)){
			int result=propertyCompanyService.updatePropertyCompany(propertyCompany);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/** 
	  * @Description(功能描述): 查询物业公司
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/9 18:45
	  **/ 
	@PostMapping("/findPropertyCompanys")
	public HttpResults findPropertyCompanys(@RequestBody PropertyCompanyEntity propertyCompany) throws Exception {
		if(CommonUtils.isNotEmpty(propertyCompany)){
			ParamDto dto=new ParamDto();
			dto.put("propertyName_where", propertyCompany.getPropertyName());
			dto.put("isValid_where", propertyCompany.getIsValid());
			dto.setPage(propertyCompany.getPage());
			dto.setRows(propertyCompany.getRows());
			PageInfo result= propertyCompanyService.findPropertyCompanys(dto);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}
}

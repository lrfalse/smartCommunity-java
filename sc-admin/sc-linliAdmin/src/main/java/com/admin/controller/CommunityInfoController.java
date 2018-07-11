package com.admin.controller;

import com.commons.controller.BaseSysApi;
import com.commons.dto.HttpResults;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.CommunityInfoEntity;
import com.commons.enums.SysCodeEnums;
import com.commons.service.sys.CommunityInfoService;
import com.commons.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** 
  * @Description(功能描述): 小区信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/11 15:15
  **/ 
@RestController
public class CommunityInfoController extends BaseSysApi {

	@Autowired
	private CommunityInfoService communityInfoService;



	/**
	  * @Description(功能描述): 保存小区信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 16:16
	  **/
	@PostMapping("/saveCommunityInfo")
	public HttpResults saveCommunityInfo(@RequestBody CommunityInfoEntity communityInfo){
		if(CommonUtils.isNotEmpty(communityInfo)){
			ParamDto dto=new ParamDto();
			dto.put("name_where", communityInfo.getName());
			dto.put("propertyId_where", communityInfo.getPropertyId());
			PageInfo counts= communityInfoService.findCommunityInfos(dto);
			if(counts.getTotal()>0){
				return getHttpResultError(SysCodeEnums.COMMUNITYINFO_EXIST);
			}
			int result=communityInfoService.saveCommunityInfo(communityInfo);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 修改小区信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 16:15
	  **/
	@PostMapping("/updateCommunityInfo")
	public HttpResults updateCommunityInfo(@RequestBody CommunityInfoEntity communityInfo){
		if(CommonUtils.isNotEmpty(communityInfo)&&CommonUtils.isNotEmpty(communityInfo.getId())){
			int result=communityInfoService.updateCommunityInfo(communityInfo);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 查询小区信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 15:06
	  **/
	@PostMapping("/findCommunityInfos")
	public HttpResults findPropertyCompanys(@RequestBody CommunityInfoEntity communityInfoEntity) throws Exception {
		if(CommonUtils.isNotEmpty(communityInfoEntity)){
			ParamDto dto=new ParamDto();
			dto.put("propertyName_where", communityInfoEntity.getPropertyName());
			dto.put("isValid_where", communityInfoEntity.getIsValid());
			dto.setPage(communityInfoEntity.getPage());
			dto.setRows(communityInfoEntity.getRows());
			PageInfo result= communityInfoService.findCommunityInfos(dto);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}
}

package com.admin.controller.housingestate;

import com.commons.controller.BaseSysApi;
import com.commons.dto.HttpResults;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.MemberEntity;
import com.commons.service.sys.MemberService;
import com.commons.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description(功能描述) :业主信息
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/7/9 17:19
 **/
@RestController
public class MemberController extends BaseSysApi {

	@Autowired
	private MemberService memberService;
	/**
	 * @Description(功能描述): 添加人员信息
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/14 11:09
	 **/
	@PostMapping("/saveMember")
	public HttpResults saveMember(@RequestBody(required = false) MemberEntity member){
		if(CommonUtils.isNotEmpty(member)){
			int result= memberService.saveMember(member);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 修改人员信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 11:12
	  **/
	@PostMapping("/updateMember")
	public HttpResults updateMember(@RequestBody (required = false)MemberEntity member){
		if(CommonUtils.isNotEmpty(member)){
			int result= memberService.updateMember(member);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}
	/**
	 * @Description(功能描述): 删除人员信息
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/20 17:24
	 **/
	@PostMapping("/delMember")
	public HttpResults delMember(@RequestBody(required = false) MemberEntity member){
		if(CommonUtils.isNotEmpty(member)){
			int result= memberService.delMember(member);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 查询业主信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/9 18:45
	  **/
	@PostMapping("/findMember")
	public HttpResults findMember(@RequestBody(required=false) MemberEntity member) throws Exception {
		ParamDto dto=new ParamDto();
		if(CommonUtils.isNotEmpty(member)){
			dto.put("isValid_where", "0");
			dto.put("roomId_where", member.getRoomId());
			dto.setPage(member.getPage());
			dto.setRows(member.getRows());
		}
		PageInfo result= memberService.findMember(dto);
		return getHttpResult(result);
	}


}

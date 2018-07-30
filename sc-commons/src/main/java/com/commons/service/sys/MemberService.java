package com.commons.service.sys;

import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.MemberEntity;
import com.commons.vo.MemberVo;
import com.commons.vo.RoomVo;
import com.github.pagehelper.PageInfo;

/** 
  * @Description(功能描述): 业主
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 10:15
  **/ 
public interface MemberService {

	int saveMember(MemberEntity member);

	int updateMember(MemberEntity member);

	int delMember(MemberEntity member);

	PageInfo<MemberVo> findMember(ParamDto paramDto);
}

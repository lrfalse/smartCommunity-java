package com.dubbo.mapper.sys;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.MemberEntity;
import com.commons.vo.MemberVo;
import com.commons.vo.RoomVo;

import java.util.List;

/**
  * @Description(功能描述): 业主信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 10:17
  **/
public interface MemberMapper extends MyMapper<MemberEntity> {


	List<MemberVo> findMember(ParamDto paramDto);
}

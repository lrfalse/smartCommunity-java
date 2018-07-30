package com.dubbo.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.MemberEntity;
import com.commons.entity.sys.MemberToRoomEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.sys.MemberToRoomService;
import com.dubbo.mapper.sys.MemberMapper;
import com.dubbo.mapper.sys.MemberToRoomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
  * @Description(功能描述): 房号
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 10:16
  **/
@Service
public class MemberToRoomServiceServiceImpl implements MemberToRoomService {

	@Autowired
    private MemberToRoomMapper memberToRoomMapper;

	public int saveMemberToRoom(MemberToRoomEntity member) {
		return memberToRoomMapper.insert(member);
	}

	public int updateMemberToRoom(MemberToRoomEntity member) {
		return memberToRoomMapper.updateByPrimaryKey(member);
	}

	public int delMemberToRoom(MemberToRoomEntity member) {
		return memberToRoomMapper.updateByPrimaryKey(member);
	}

}

package com.dubbo.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.MemberEntity;
import com.commons.entity.sys.MemberToRoomEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.sys.MemberService;
import com.commons.service.sys.MemberToRoomService;
import com.commons.vo.MemberVo;
import com.commons.vo.RoomVo;
import com.dubbo.mapper.sys.MemberMapper;
import com.dubbo.mapper.sys.MemberToRoomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
  * @Description(功能描述): 用户信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 10:16
  **/
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
	@Autowired
    private MemberToRoomMapper memberToRoomMapper;

	public int saveMember(MemberEntity member) {
    	member.setIsValid(0);
		member.setCreateDate(new Date());
		int result= memberMapper.insert(member);
		if(result>0){
			MemberToRoomEntity memberToRoom=new MemberToRoomEntity(member.getRoomId(),member.getId());
			result=memberToRoomMapper.insert(memberToRoom);
		}else{
			throw new ScException(AppServiceEnums.SYS_EXCEPTION);
		}
		return result;
	}

	public int updateMember(MemberEntity member) {
		return memberMapper.updateByPrimaryKey(member);
	}

	public int delMember(MemberEntity member) {
		member.setIsValid(1);
		return memberMapper.updateByPrimaryKey(member);
	}

    public PageInfo<MemberVo> findMember(ParamDto paramDto) {
		PageHelper.startPage(paramDto.getPage(),paramDto.getRows(),"createDate desc");
        return new PageInfo<>( memberMapper.findMember(paramDto));
    }


}

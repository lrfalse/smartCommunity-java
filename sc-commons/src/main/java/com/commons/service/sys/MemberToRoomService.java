package com.commons.service.sys;

import com.commons.entity.sys.MemberToRoomEntity;

/** 
  * @Description(功能描述): 业主
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 10:15
  **/ 
public interface MemberToRoomService {

	int saveMemberToRoom(MemberToRoomEntity memberToRoom);

	int updateMemberToRoom(MemberToRoomEntity memberToRoom);

	int delMemberToRoom(MemberToRoomEntity memberToRoom);

}

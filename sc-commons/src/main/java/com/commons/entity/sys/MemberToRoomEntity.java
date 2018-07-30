package com.commons.entity.sys;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "membertoroom")
public class MemberToRoomEntity extends BaseIdEntity {
	@Column(name="roomId")
    private Long roomId;
	@Column(name="memberId")
    private Long memberId;

	public MemberToRoomEntity(Long roomId, Long memberId) {
		this.roomId = roomId;
		this.memberId = memberId;
	}
	public MemberToRoomEntity() {
	}
}
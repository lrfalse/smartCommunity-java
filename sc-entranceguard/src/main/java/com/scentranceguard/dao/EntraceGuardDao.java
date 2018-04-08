package com.scentranceguard.dao;

import com.scentranceguard.from.FaceCheck;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:门禁类
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-08 14:14:29
 */
@Mapper
public interface EntraceGuardDao {

    @Insert("INSERT INTO linlihouse.face_check(username,phonenumber,community,housenumber,doortype,imageurl,createDate) VALUES (#{username},#{phonenumber},#{community},#{housenumber},#{doortype},#{imageurl},now())")
    void saveimage(FaceCheck faceCheck);
}

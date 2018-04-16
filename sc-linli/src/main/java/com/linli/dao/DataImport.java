package com.linli.dao;

import com.linli.form.SqlProvider;
import com.linli.form.T_community_info;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:数据导入接口
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-12 11:11:05
 */
@Mapper
public interface DataImport {


    @InsertProvider(type = SqlProvider.class,method ="insetData" )
    void batchSave(@Param("list")List<T_community_info> list);

    @Select("SELECT t.g_location FROM linlihouse.t_community_info t")
    List<String> query();

    /*@Insert("INSERT INTO linlihouse.t_community_info(name, address, g_location, b_location, postcode, pcode, " +
            "pname, citycode, cityname, adcode, adname, entr_location, photos_url, status, remark)VALUES (#{name},#{address}," +
            "#{g_location},#{b_location},#{postcode},#{pcode},#{pname},#{citycode},#{cityname},#{adcode},#{adname}," +
            "#{entr_location},#{photos_url},#{status},#{remark})")
    void batchSave1(T_community_info t);*/
}

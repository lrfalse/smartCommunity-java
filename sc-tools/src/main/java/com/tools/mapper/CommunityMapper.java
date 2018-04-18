package com.tools.mapper;
import com.commons.config.MyMapper;
import com.commons.entity.CommunityEntity;
import com.tools.from.SqlProvider;
import com.tools.from.T_community_info;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @Description:小区数据导入接口
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-12 11:11:05
 */
public interface CommunityMapper extends MyMapper<CommunityEntity>{

   /*
    @InsertProvider(type = SqlProvider.class,method ="insetData" )
    void batchSave(@Param("list") List<T_community_info> list);*/

   // @Select("SELECT t.g_location FROM linlihouse.t_community_info t")
    List<String> queryLocation();

}

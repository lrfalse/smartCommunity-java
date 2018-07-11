package com.dubbo.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.CommunityInfoEntity;
import com.commons.service.sys.CommunityInfoService;
import com.dubbo.mapper.sys.CommunityInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
  * @Description(功能描述): 小区信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 14:54
  **/
@Service
public class CommuntiyInfoServiceImpl implements CommunityInfoService {

    @Autowired
    private CommunityInfoMapper communityMapper;

	/**
	 * @Description(功能描述): 保存
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/9 17:17
	 **/
	public int saveCommunityInfo(CommunityInfoEntity communityInfo){
		communityInfo.setIsValid(0);
		return communityMapper.insert(communityInfo);
	}
	/**
	 * @Description(功能描述): 修改物业信息
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/9 21:12
	 **/
	public int updateCommunityInfo(CommunityInfoEntity communityInfo){
		return communityMapper.updateByPrimaryKey(communityInfo);
	}
    /**
      * @Description(功能描述): 查询小区信息
      * @author(作者): lrfalse<wangliyou>
      * @date(开发日期): 2018/7/10 14:54
      **/
    public PageInfo<CommunityInfoEntity> findCommunityInfos(ParamDto paramDto) {
        PageHelper.startPage(paramDto.getPage(),paramDto.getRows());
        return new PageInfo<>( communityMapper.findCommunityInfos(paramDto));
    }


}

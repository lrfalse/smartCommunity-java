package com.linliHouse.controller;

import com.alibaba.fastjson.JSON;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.entity.NannyEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.NannyService;
import com.commons.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * @Description:保姆
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018/05/08 11:11:03
 */
@RestController
public class NannyController extends BaseApi{

    @Autowired
    private NannyService nannyService;
    /**
      * @Description(功能描述): 加入我们
      * @author(作者): feihong
      * @date (开发日期):2018/5/8 11:53
      **/
    @PostMapping("inJoin")
    public HttpResults inJoin(HttpServletRequest req) throws Exception {
        NannyEntity entity = JSON.parseObject(getIsJson(req).getBodyJson(), NannyEntity.class);
        if (CommonUtils.isNotEmpty(entity.getName()) && CommonUtils.isNotEmpty(entity.getAge()) && CommonUtils.isNotEmpty(entity.getMobPhone()) && CommonUtils.isNotEmpty(entity.getAddress())){
            int i = nannyService.inJoin(entity);
            if (i<0){
                throw new ScException(AppServiceEnums.SYS_EXCEPTION);
            }
            return getHttpResult(i);
        }
       throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
    }

    @PostMapping("queryNanny")
    public HttpResults queryNanny(HttpServletRequest req) throws Exception {
        NannyEntity entity = JSON.parseObject(getIsJson(req).getBodyJson(), NannyEntity.class);
        List<NannyEntity> entities = nannyService.queryNanny(entity);
        return getHttpResult(entities);
    }
}

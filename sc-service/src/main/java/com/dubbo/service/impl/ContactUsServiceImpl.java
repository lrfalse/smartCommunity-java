package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.entity.ContactUsEntity;
import com.commons.service.ContactUsService;
import com.dubbo.mapper.ContactUsMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description(功能描述) : 联系我们
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 10:46
 */
@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsMapper contactUsMapper;

    /**
     * @Description(功能描述) : 联系我们
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 10:47
     */
    @Override
    public ContactUsEntity getContactUs() {
        List<ContactUsEntity> list = contactUsMapper.selectAll();
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}

package com.personalCenter.controller;

import com.alibaba.fastjson.JSON;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.entity.FeedBackEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @Description:意见反馈
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018/05/03 11:11:32
 */
@RestController
public class FeedbackController extends BaseApi {

    private static final Logger logger= LoggerFactory.getLogger(FeedbackController.class);
    /**
     * @Description(功能描述): 用户意见反馈
     * @author(作者): feihong
     * @date (开发日期):2018/5/3 11:34
     **/
    @PostMapping("feedback")
    public String feedback(HttpServletRequest req) {
        FeedBackEntity backEntity = JSON.parseObject(getIsJson(req).getBodyJson(), FeedBackEntity.class);
        List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("file");
        StringBuffer sb=new StringBuffer();
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        String filePath="E:\\"+"upload"+"\\";
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                    sb.append(filePath+file.getOriginalFilename()+",");
                } catch (Exception e) {
                    logger.info("上传文件失败"+i+":"+e.getMessage());
                }
            } else {
                logger.info("没有选者文件,请选择文件"+i);
            }
        }

        return "upload successful";
    }
}

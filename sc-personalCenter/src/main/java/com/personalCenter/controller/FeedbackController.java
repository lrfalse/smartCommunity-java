package com.personalCenter.controller;

import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.anDto.LoginDTO;
import com.commons.dto.reDto.FeedBackReDto;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.UserService;
import com.commons.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:意见反馈
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018/05/03 11:11:32
 */
@Controller
public class FeedbackController extends BaseApi {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Value("${sc.feedback.img.url}")
    private String path;

    @Autowired
    private UserService userService;

    @GetMapping("forgotpwd")
    public String indexdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.sendRedirect(request.getContextPath() + "templates/index.html");
        return "index";
    }

    /**
     * @Description(功能描述): 用户意见反馈
     * @author(作者): feihong
     * @date (开发日期):2018/5/3 11:34
     **/
    @PostMapping("feedback")
    public HttpResults feedback7(FeedBackReDto backEntity, @RequestParam("files") MultipartFile[] files) throws Exception {
        // FeedBackReDto backEntity = JSON.parseObject(getIsJson(req).getBodyJson(), FeedBackReDto.class);
        String token = backEntity.getToken();
        LoginDTO redisUser = userService.getRedisUser(backEntity.getToken());
        if (CommonUtils.isEmpty(redisUser)) {
            throw new ScException(AppServiceEnums.NULL_USER_DATA);
        } else {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                if (files[i] != null) {
                    //调用上传方法
                    String fileName = executeUpload(files[i]);
                    list.add(fileName);
                }
            }
            backEntity.setUserId(String.valueOf(redisUser.getUserId()));
            backEntity.setList(list);
            int i = userService.addFeedBack(backEntity);
            return getHttpResult(i);
        }
    }


    /**
     * 提取上传方法为公共方法
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String executeUpload(MultipartFile file) {

        String fileName = file.getOriginalFilename();   //文件名
        String pathUrl = path + fileName;
        //服务端保存的文件对象
        File serverFile = new File(path + fileName);
        // 检测是否存在目录
        if (!serverFile.getParentFile().exists()) {
            serverFile.getParentFile().mkdirs();
        }
        //将上传的文件写入到服务器端文件内
        try {
            file.transferTo(serverFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ScException(AppServiceEnums.FILE_UPLOAD_EEROR);
        }
        return pathUrl;
    }

}

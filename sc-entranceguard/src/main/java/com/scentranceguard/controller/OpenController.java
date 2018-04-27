package com.scentranceguard.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.entity.FaceEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.FaceRegisterService;
import com.commons.utils.HttpClientUtil;
import com.scentranceguard.from.*;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:开门
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-25 18:18:38
 */
@RestController
public class OpenController extends BaseApi{

    private static final Logger logger= LoggerFactory.getLogger(OpenController.class);

    private final String url="http://open.meilinapi.com/";

    @Autowired
    private FaceRegisterService faceRegisterService;

    /**
      * @Description(功能描述): 人脸图片注册
      * @author(作者): feihong
      * @date (开发日期):2018-4-26 11:00
      **/
    @PostMapping("faceRegister")
    public HttpResults faceRegister(HttpServletRequest req, @RequestParam("file") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();   //获取文件名称
        String filePath = "E:\\"+System.currentTimeMillis()/1000+"\\"+"image"+"\\";
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        FaceEntity face = JSON.parseObject(getIsJson(req).getBodyJson(), FaceEntity.class);
        file.transferTo(dest);
        face.setImage_url(filePath + fileName);
        return getHttpResult(faceRegisterService.addFace(face));
    }
   /**
      * @Description(功能描述): 远程开门
      * @author(作者): feihong
      * @date (开发日期):2018-4-25 20:53
      **/
   @PostMapping("remoteDoor")
    public HttpResults remoteDoor(HttpServletRequest req) throws Exception {
        RemoteOpenDoor remoteOpenDoor = JSON.parseObject(getIsJson(req).getBodyJson(), RemoteOpenDoor.class);
        String s = HttpClientUtil.getInstance().sendHttpPost(url, bulidRemote(remoteOpenDoor));
        if (isDoor(s)){
            RemoteReturn remoteReturn = JSON.parseObject(s, RemoteReturn.class);
            return getHttpResult(remoteReturn);
        }
       logger.info("参数不正确,请检查参数");
       return getHttpResultError();
    }
    /**
      * @Description(功能描述): 密码开门
      * @author(作者): feihong
      * @date (开发日期):2018-4-25
      **/
    @PostMapping("door")
    public HttpResults pwdDoor(HttpServletRequest req) throws Exception {
        PasswordOpenDoor openDoor = JSON.parseObject(getIsJson(req).getBodyJson(), PasswordOpenDoor.class);
       //二维码开门
        if(openDoor.getA().equals("get_smdqrc")){
            String s = HttpClientUtil.getInstance().sendHttpPost(url, bulidPassWord(openDoor));
            if (isDoor(s)){
                CodeOpenDoor door = JSON.parseObject(s, CodeOpenDoor.class);
                return getHttpResult(door);
            }
            logger.info("参数不正确,请检查参数");
          return getHttpResultError();
        }
        //获取远程密钥
        if (openDoor.getA().equals("get_net_open_key")){
            String s = HttpClientUtil.getInstance().sendHttpPost(url, bulidPassWord(openDoor));
            if (isDoor(s)){
                RemoteReturnSecert rermoteSecert = JSON.parseObject(s, RemoteReturnSecert.class);
                return getHttpResult(rermoteSecert);
            }
            logger.info("参数不正确,请检查参数");
         return getHttpResultError();
        }
        //获取mac地址
        if (openDoor.getA().equals("get_smdkey")){
            String s = HttpClientUtil.getInstance().sendHttpPost(url, bulidPassWord(openDoor));
            if (isDoor(s)){
                BluetoothData bluetoothData = JSON.parseObject(s, BluetoothData.class);
                return getHttpResult(bluetoothData);
            }
            logger.info("参数不正确,请检查参数");
            return getHttpResultError();
        }
        //密码开门
        if (openDoor.getA().equals("get_smdpwd")){
            String s = HttpClientUtil.getInstance().sendHttpPost(url, bulidPassWord(openDoor));
            if (isDoor(s)){
                PasswordReturnData passwordData = JSON.parseObject(s, PasswordReturnData.class);
                return getHttpResult(passwordData);
            }
            logger.info("参数不正确,请检查参数");
            return getHttpResultError();
        }
      throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
    }
    /**
      * @Description(功能描述): 获取access_token
      * @author(作者): feihong
      * @date (开发日期):2018-04-25 18:18:38
      **/
    @PostMapping("secret")
    public HttpResults getAccessToken(HttpServletRequest req) throws Exception {
        Secret secret = JSON.parseObject(getIsJson(req).getBodyJson(), Secret.class);
        String s = HttpClientUtil.getInstance().sendHttpPost(url, bulidSercret(secret));
        if (isDoor(s)){
            SecertReturnData secertData = JSON.parseObject(s, SecertReturnData.class);
            return getHttpResult(secertData);
        }
        logger.info("参数不正确,请检查参数");
       return getHttpResultError();
    }
    /**
      * @Description(功能描述):密钥参数构建
      * @author(作者): feihong
      * @date (开发日期):2018-04-25 20:31:38
      **/
    public Map<String,String> bulidSercret(Secret secret){
        Map<String,String> map= new HashMap<>();
        map.put("a",secret.getA());
        map.put("m",secret.getM());
        map.put("f",secret.getF());
        map.put("app_id",secret.getApp_id());
        map.put("app_secret",secret.getApp_secret());
        map.put("effective_days",secret.getEffective_days());
        return map;
    }

    /**
      * @Description(功能描述): 密码开门参数构建
      * @author(作者): feihong
      * @date (开发日期):2018-4-25 20:32:22
      **/
    public Map<String,String> bulidPassWord(PasswordOpenDoor openDoor){
        Map<String,String> map= new HashMap<>();
        map.put("a",openDoor.getA());
        map.put("m",openDoor.getM());
        map.put("f",openDoor.getF());
        map.put("app_id",openDoor.getApp_id());
        map.put("app_secret",openDoor.getApp_secret());
        map.put("access_token",openDoor.getAccess_token());
        map.put("device_sncode",openDoor.getDevice_sncode());
        return map;
    }

    /**
      * @Description(功能描述): 远程开门参数构建
      * @author(作者): feihong
      * @date (开发日期):2018-4-25 21:41
      **/
    public Map<String,String> bulidRemote(RemoteOpenDoor remoteOpenDoor){
        Map<String,String> map= new HashMap<>();
        map.put("a",remoteOpenDoor.getA());
        map.put("m",remoteOpenDoor.getM());
        map.put("f",remoteOpenDoor.getF());
        map.put("app_id",remoteOpenDoor.getApp_id());
        map.put("app_secret",remoteOpenDoor.getApp_secret());
        map.put("access_token",remoteOpenDoor.getAccess_token());
        map.put("device_sncode",remoteOpenDoor.getDevice_sncode());
        map.put("net_open_key",remoteOpenDoor.getNet_open_key());
        return map;
    }

    /**
      * @Description(功能描述): 开门是否成功
      * @author(作者): feihong
      * @date (开发日期):2018-4-25 22:10
      **/
    public boolean isDoor(String s){
        JSONObject jsonObject = JSON.parseObject(s);
        int i = (int)jsonObject.get("state");
        if (i==1){
            return true;
        }
        return false;
    }
}

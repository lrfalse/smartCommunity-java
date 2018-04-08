package com.scentranceguard.controller;

import com.alibaba.fastjson.JSON;
import com.scentranceguard.from.*;
import com.scentranceguard.service.EntraceGuardService;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class OpenController {

    @Autowired
    EntraceGuardService entraceGuardService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenController.class);

    @RequestMapping(value = "indexTest")
    public String indexTest(){
            return "index";
    }
    /**
     * @Description人脸注册
     * @Param form
     * @Return Object
     **/
    @PostMapping("/index")
    @ResponseBody
    public Object faceCheck(@RequestBody FaceCheck faceCheck, @RequestParam("face")MultipartFile file, HttpServletRequest request) {
        //获取文件名称
        String fileName = file.getOriginalFilename();
        LOGGER.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        LOGGER.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "E:\\"+System.currentTimeMillis()/1000+"\\"+"face"+"\\";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            faceCheck.setImageurl(filePath + fileName);
            entraceGuardService.saveImage(faceCheck);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    /**
     * @Description 远程开门
     * @Param
     * @Return 1:开门成功 ,0:开门失败
     **/
    @PostMapping("remoteropendoor")
    @ResponseBody
    public Object remoteOpenDoor(@RequestBody RemoteOpenDoor remoteOpenDoor) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost("http://open.meilinapi.com/");
        // 创建参数队列
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(bulidRemoteOpenDoor(remoteOpenDoor), "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);
            RemoteReturn remoteReturn = JSON.parseObject(s, RemoteReturn.class);
            httpclient.close();
            response.close();
            return remoteReturn;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "开门失败";
    }

    /**
     * 二维码开门
     */
    @PostMapping("code/open")
    @ResponseBody
    public Object codeOpen(@RequestBody PasswordOpenDoor passwordOpenDoor) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost("http://open.meilinapi.com/");
        // 创建参数队列
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(bulidPasswordOpen(passwordOpenDoor), "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);
            CodeOpenDoor codeOpenDoor = JSON.parseObject(s, CodeOpenDoor.class);
            httpclient.close();
            response.close();
            return codeOpenDoor;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "二维码获取失败";
    }

    /**
     * 密码开门
     */
    @PostMapping("password/open")
    @ResponseBody
    public Object passwordOpen( PasswordOpenDoor passwordOpenDoor) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost("http://open.meilinapi.com/");
        // 创建参数队列
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(bulidPasswordOpen(passwordOpenDoor), "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);
            //A:为get_smdpwd表示密码开门,否则为蓝牙开门
            if(passwordOpenDoor.getA().equals("get_smdpwd")){
                PasswordReturnData passwordReturnData = JSON.parseObject(s, PasswordReturnData.class);
                httpclient.close();
                response.close();
                return passwordReturnData;
            }else {
                BluetoothData bluetoothData = JSON.parseObject(s, BluetoothData.class);
                httpclient.close();
                response.close();
                return bluetoothData;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "密码获取失败";
    }

    /**
     * 获取远程access_token
     */
    @PostMapping("remote/secret")
    @ResponseBody
    public Object getRemoteSecert(@RequestBody PasswordOpenDoor passwordOpenDoor) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost("http://open.meilinapi.com/");
        // 创建参数队列
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(bulidPasswordOpen(passwordOpenDoor), "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);
            RemoteReturnSecert remoteReturnSecert = JSON.parseObject(s, RemoteReturnSecert.class);
            httpclient.close();
            response.close();
            return remoteReturnSecert;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "远程密钥获取失败";
    }

    /**
     * 获取access_token
     */
    @PostMapping("secert")
    @ResponseBody
    public Object getSecertt(@RequestBody Secert secert) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost("http://open.meilinapi.com/");
        // 创建参数队列
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(bulidGetSecert(secert), "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);
            SecertReturnData passwordReturnData = JSON.parseObject(s, SecertReturnData.class);
            httpclient.close();
            response.close();
            return passwordReturnData;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "密钥获取失败";
    }

    public List bulidGetSecert(Secert secert) {
        List list = new ArrayList();
        list.add(new BasicNameValuePair("a", secert.getA()));
        list.add(new BasicNameValuePair("m", secert.getM()));
        list.add(new BasicNameValuePair("f", secert.getF()));
        list.add(new BasicNameValuePair("app_id", secert.getApp_id()));
        list.add(new BasicNameValuePair("app_secret", secert.getApp_secret()));
        list.add(new BasicNameValuePair("effective_days", secert.getEffective_days()));
        return list;
    }

    public List bulidPasswordOpen(PasswordOpenDoor passwordOpenDoor) {
        List list = new ArrayList();
        list.add(new BasicNameValuePair("a", passwordOpenDoor.getA()));
        list.add(new BasicNameValuePair("m", passwordOpenDoor.getM()));
        list.add(new BasicNameValuePair("f", passwordOpenDoor.getF()));
        list.add(new BasicNameValuePair("access_token", passwordOpenDoor.getAccess_token()));
        list.add(new BasicNameValuePair("app_id", passwordOpenDoor.getApp_id()));
        list.add(new BasicNameValuePair("app_secret", passwordOpenDoor.getApp_secret()));
        list.add(new BasicNameValuePair("device_sncode", passwordOpenDoor.getDevice_sncode()));
        return list;
    }

    public List bulidRemoteOpenDoor(RemoteOpenDoor remoteOpenDoor) {
        List list = new ArrayList();
        list.add(new BasicNameValuePair("a", remoteOpenDoor.getA()));
        list.add(new BasicNameValuePair("m", remoteOpenDoor.getM()));
        list.add(new BasicNameValuePair("f", remoteOpenDoor.getF()));
        list.add(new BasicNameValuePair("access_token", remoteOpenDoor.getAccess_token()));
        list.add(new BasicNameValuePair("app_id", remoteOpenDoor.getApp_id()));
        list.add(new BasicNameValuePair("app_secret", remoteOpenDoor.getApp_secret()));
        list.add(new BasicNameValuePair("device_sncode", remoteOpenDoor.getDevice_sncode()));
        list.add(new BasicNameValuePair("net_open_key", remoteOpenDoor.getNet_open_key()));
        return list;
    }

    /**
     * 判断文件是否为图片文件
     * @param fileName
     * @return
     */
    private Boolean isImageFile(String fileName) {
        String [] img_type = new String[]{".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        if(fileName==null) {return false;}
        fileName = fileName.toLowerCase();
        for(String type : img_type) {
            if(fileName.endsWith(type)) {return true;}
        }
        return false;
    }
    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    private String getFileType(String fileName) {
        if(fileName!=null && fileName.indexOf(".")>=0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }
    /**
      *@Description:创建目录
      *@Param:
      *@create:${YEAR}-${MONTH}-${DAY} ${HOUR}:${TIME}
      *@Return:
      **/
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }
}

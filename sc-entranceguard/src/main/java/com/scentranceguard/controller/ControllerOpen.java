//package com.scentranceguard.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.scentranceguard.from.*;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Api("开门")
//@RestController
//public class ControllerOpen {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerOpen.class);
//
//    /**
//     * @Description人脸注册
//     * @Param form
//     * @Return Object
//     **/
//    @PostMapping("faceregister")
//    public Object faceRegister(MultipartFile file) {
//
//        return "人脸注册失败";
//    }
//
//    /**
//     * @Description 远程开门
//     * @Param
//     * @Return 1:开门成功 ,0:开门失败
//     **/
//    @PostMapping("remoteropendoor")
//    public Object remoteOpenDoor(@RequestBody RemoteOpenDoor remoteOpenDoor) {
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost httppost = new HttpPost("http://open.meilinapi.com/");
//        // 创建参数队列
//        UrlEncodedFormEntity uefEntity;
//        try {
//            uefEntity = new UrlEncodedFormEntity(bulidRemoteOpenDoor(remoteOpenDoor), "UTF-8");
//            httppost.setEntity(uefEntity);
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            HttpEntity entity = response.getEntity();
//            String s = EntityUtils.toString(entity);
//            RemoteReturn remoteReturn = JSON.parseObject(s, RemoteReturn.class);
//            System.out.println("s" + s);
//            httpclient.close();
//            response.close();
//            return remoteReturn;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "开门失败";
//    }
//
//    /**
//     * 二维码开门
//     */
//    @ApiOperation(value = "获图书细信息", notes = "根据url的id来获取详细信息")
//    @ApiImplicitParam(name = "passwordOpenDoor", value = "入参", required = true, dataType = "PasswordOpenDoor")
//    @PostMapping("code/open")
//    public Object codeOpen(@RequestBody PasswordOpenDoor passwordOpenDoor) {
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost httppost = new HttpPost("http://open.meilinapi.com/");
//        // 创建参数队列
//        UrlEncodedFormEntity uefEntity;
//        try {
//            uefEntity = new UrlEncodedFormEntity(bulidPasswordOpen(passwordOpenDoor), "UTF-8");
//            httppost.setEntity(uefEntity);
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            HttpEntity entity = response.getEntity();
//            String s = EntityUtils.toString(entity);
//            CodeOpenDoor codeOpenDoor = JSON.parseObject(s, CodeOpenDoor.class);
//            System.out.println("s" + s);
//            httpclient.close();
//            response.close();
//            return codeOpenDoor;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "二维码获取失败";
//    }
//
//    /**
//     * 密码开门
//     */
//    @PostMapping("password/open")
//    public Object passwordOpen(@RequestBody PasswordOpenDoor passwordOpenDoor) {
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost httppost = new HttpPost("http://open.meilinapi.com/");
//        // 创建参数队列
//        UrlEncodedFormEntity uefEntity;
//        try {
//            uefEntity = new UrlEncodedFormEntity(bulidPasswordOpen(passwordOpenDoor), "UTF-8");
//            httppost.setEntity(uefEntity);
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            HttpEntity entity = response.getEntity();
//            String s = EntityUtils.toString(entity);
//            PasswordReturnData passwordReturnData = JSON.parseObject(s, PasswordReturnData.class);
//            System.out.println("s" + s);
//            httpclient.close();
//            response.close();
//            return passwordReturnData;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "密码获取失败";
//    }
//
//    /**
//     * 获取远程access_token
//     */
//    @PostMapping("remote/secret")
//    public Object getRemoteSecert(@RequestBody PasswordOpenDoor passwordOpenDoor) {
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost httppost = new HttpPost("http://open.meilinapi.com/");
//        // 创建参数队列
//        UrlEncodedFormEntity uefEntity;
//        try {
//            uefEntity = new UrlEncodedFormEntity(bulidPasswordOpen(passwordOpenDoor), "UTF-8");
//            httppost.setEntity(uefEntity);
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            HttpEntity entity = response.getEntity();
//            String s = EntityUtils.toString(entity);
//            RemoteReturnSecert remoteReturnSecert = JSON.parseObject(s, RemoteReturnSecert.class);
//            System.out.println("s" + s);
//            httpclient.close();
//            response.close();
//            return remoteReturnSecert;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "远程密钥获取失败";
//    }
//
//    /**
//     * 获取access_token
//     */
//    @PostMapping("secert")
//    public Object getSecertt(@RequestBody Secert secert) {
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost httppost = new HttpPost("http://open.meilinapi.com/");
//        // 创建参数队列
//        UrlEncodedFormEntity uefEntity;
//        try {
//            uefEntity = new UrlEncodedFormEntity(bulidGetSecert(secert), "UTF-8");
//            System.out.println("executing request " + httppost.getURI());
//            httppost.setEntity(uefEntity);
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            HttpEntity entity = response.getEntity();
//            String s = EntityUtils.toString(entity);
//            SecertReturnData passwordReturnData = JSON.parseObject(s, SecertReturnData.class);
//            System.out.println("s" + s);
//            httpclient.close();
//            response.close();
//            return passwordReturnData;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "密钥获取失败";
//    }
//
//    public List bulidGetSecert(Secert secert) {
//        List list = new ArrayList();
//        list.add(new BasicNameValuePair("a", secert.getA()));
//        list.add(new BasicNameValuePair("m", secert.getM()));
//        list.add(new BasicNameValuePair("f", secert.getF()));
//        list.add(new BasicNameValuePair("app_id", secert.getApp_id()));
//        list.add(new BasicNameValuePair("app_secret", secert.getApp_secret()));
//        list.add(new BasicNameValuePair("effective_days", secert.getEffective_days()));
//        return list;
//    }
//
//    public List bulidPasswordOpen(PasswordOpenDoor passwordOpenDoor) {
//        List list = new ArrayList();
//        list.add(new BasicNameValuePair("a", passwordOpenDoor.getA()));
//        list.add(new BasicNameValuePair("m", passwordOpenDoor.getM()));
//        list.add(new BasicNameValuePair("f", passwordOpenDoor.getF()));
//        list.add(new BasicNameValuePair("access_token", passwordOpenDoor.getAccess_token()));
//        list.add(new BasicNameValuePair("app_id", passwordOpenDoor.getApp_id()));
//        list.add(new BasicNameValuePair("app_secret", passwordOpenDoor.getApp_secret()));
//        list.add(new BasicNameValuePair("device_sncode", passwordOpenDoor.getDevice_sncode()));
//        return list;
//    }
//
//    public List bulidRemoteOpenDoor(RemoteOpenDoor remoteOpenDoor) {
//        List list = new ArrayList();
//        list.add(new BasicNameValuePair("a", remoteOpenDoor.getA()));
//        list.add(new BasicNameValuePair("m", remoteOpenDoor.getM()));
//        list.add(new BasicNameValuePair("f", remoteOpenDoor.getF()));
//        list.add(new BasicNameValuePair("access_token", remoteOpenDoor.getAccess_token()));
//        list.add(new BasicNameValuePair("app_id", remoteOpenDoor.getApp_id()));
//        list.add(new BasicNameValuePair("app_secret", remoteOpenDoor.getApp_secret()));
//        list.add(new BasicNameValuePair("device_sncode", remoteOpenDoor.getDevice_sncode()));
//        list.add(new BasicNameValuePair("net_open_key", remoteOpenDoor.getNet_open_key()));
//        return list;
//    }
//
//
//}

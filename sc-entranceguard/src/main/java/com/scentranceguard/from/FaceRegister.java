package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:人脸注册form
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-02 16:16:05
 */
@Data
public class FaceRegister extends PasswordOpenDoor implements Serializable{

    /**
     *人脸图片
     */
    private String face_img;
}

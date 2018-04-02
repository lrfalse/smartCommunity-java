package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:远程开门bean
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-02 17:17:15
 */
@Data
public class RemoteOpenDoor extends PasswordOpenDoor implements Serializable{

    private String net_open_key;
}

package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;

@Data
public class SRemote implements Serializable{

     /**
      *远程密钥
      */
     private String net_open_key;
}

package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;

@Data
public class CodeOpenDoor extends CodeOpen implements Serializable {
    private String state;

    private String ico;

    private String data_ver;

}

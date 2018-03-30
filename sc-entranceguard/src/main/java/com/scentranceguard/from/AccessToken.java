package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccessToken  implements Serializable {

    private Access return_data;
}

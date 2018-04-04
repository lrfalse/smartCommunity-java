package com.scentranceguard.from;

import lombok.Data;

/**
 * @Description:蓝牙开门返回值
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-04 15:15:29
 */
@Data
public class BluetoothData {
    /**
     *状态 1:成功, 0:失败
     */
    private String state;

    /**
     *信息提示 error:显示错误,success:显示正确
     */
    private String ico;

    /**
     *
     */
    private String data_ver;

    /**
     *蓝牙mac信息
     */
    private Bluetooth return_data;
}

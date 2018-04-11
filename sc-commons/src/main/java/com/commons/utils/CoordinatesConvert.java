package com.commons.utils;

/**
 * @Description:高德坐标转百度坐标
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-11 16:16:30
 */
public class CoordinatesConvert {

  public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0 ;

    /**
      *@Description:保留小数后6位
      *@Param:
      *@create:2018-04-11 16:16:30
      *@Return:
      **/
    private static double retain6(double num){
        String result = String .format("%.6f", num);
        return Double.valueOf(result);
    }

    /**
      *@Description: 高德坐标转百度坐标
      *@Param:
      *@create:2018-04-11 16:16:30
      *@Return:
      **/
    public static double[] gcj02tobd09(double lng, double lat) {
        double z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * x_pi);
        double theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * x_pi);
        double bd_lng = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new double[] { bd_lng, bd_lat };
    }

}

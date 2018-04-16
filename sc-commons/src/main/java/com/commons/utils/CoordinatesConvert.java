package com.commons.utils;

import java.util.ArrayList;
import java.util.List;

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
    public static double retain6(double num){
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

    /**
      * @Description(功能描述): 经纬度换算
      * @author(作者): lrfalse<wangliyou>
      * @date (开发日期): 2018/4/16 13:55
	 * @param lcation  经纬度
      **/
    public static double[] gcj02tobd09(String lcation) {
    	String[] gblcation=lcation.split(",");
		double lng=Double.valueOf(gblcation[0]);
		double lat=Double.valueOf(gblcation[1]);
        return gcj02tobd09(lng,lat);
    }

    /**
      * @Description(功能描述): 经纬度转换
      * @author(作者): lrfalse<wangliyou>
      * @date (开发日期): 2018/4/16 14:02
	 * @param lcation :经纬度
      **/
	public static String convert(String lcation) {
		StringBuffer sb=new StringBuffer("");
		double[] gbLcation = gcj02tobd09(lcation);
		sb.append(CoordinatesConvert.retain6(gbLcation[0]));
		sb.append(",");
		sb.append(CoordinatesConvert.retain6(gbLcation[1]));
		return sb.toString();
	}
	public static void main(String[] args){
	    System.out.println(convert("113.26641,23.132324"));
	}
}

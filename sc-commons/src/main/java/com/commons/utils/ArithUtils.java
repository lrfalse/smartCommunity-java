package com.commons.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
  * @Description(功能描述): 数字运算
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/3/30 14:38
  **/
public class ArithUtils
{

    private static final int DEF_DIV_SCALE = 10;

    private ArithUtils()
    {
    }

    /**
      * @Description(功能描述): 加法
      * @author(作者): lrfalse<wangliyou>
      * @date (开发日期): 2018/3/30 14:38
      **/
    public static String add(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.valueOf(v1).toString());
        BigDecimal b2 = new BigDecimal(Double.valueOf(v2).toString());
        return b1.add(b2).toString();
    }

    public static String add(String v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(Double.valueOf(v2).toString());
        return b1.add(b2).toString();
    }

    public static String add(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).toString();
    }

    /**
      * @Description(功能描述):减法
      * @author(作者): lrfalse<wangliyou>
      * @date (开发日期): 2018/3/30 14:38
      **/
    public static String sub(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).toString();
    }

    /**
      * @Description(功能描述): v1 减v2和v3
      * @author(作者): lrfalse<wangliyou>
      * @date (开发日期): 2018/3/30 14:38
      **/
	public static String sub(String v1, String v2,String v3)
	{
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		BigDecimal b3 = new BigDecimal(v3);
		return b1.subtract(b2).subtract(b3).toString();
	}

    public static String sub(String v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(Double.valueOf(v2).toString());
        return b1.subtract(b2).toString();
    }

    public static String sub(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.valueOf(v1).toString());
        BigDecimal b2 = new BigDecimal(Double.valueOf(v2).toString());
        return b1.subtract(b2).toString();
    }



    /**
      * @Description(功能描述): 乘法
      * @author(作者): lrfalse<wangliyou>
      * @date (开发日期): 2018/3/30 14:38
      **/
    public static String mul(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).toString();
    }

    /**
      * @Description(功能描述): 除法
      * @author(作者): lrfalse<wangliyou>
      * @date (开发日期): 2018/3/30 14:39
      **/
    public static String div(String v1, String v2)
    {
        return div(v1, v2, 10);
    }

    public static String div(String v1, String v2, int scale)
    {
        if(scale < 0)
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        if(v2.equals("0"))
        {
            return "0";
        } else
        {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.divide(b2, scale, 4).toString();
        }
    }

    public static String div(double v1, double v2, int scale)
    {
        if(scale < 0)
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        if(v2 == 0.0D)
        {
            return "0";
        } else
        {
            BigDecimal b1 = new BigDecimal(Double.valueOf(v1).toString());
            BigDecimal b2 = new BigDecimal(Double.valueOf(v2).toString());
            return b1.divide(b2, scale, 4).toString();
        }
    }


    /**
      * @Description(功能描述): 四舍五入
      * @author(作者): lrfalse<wangliyou>
      * @date (开发日期): 2018/3/30 14:39
      **/
    public static String round(String v, int scale)
    {
        if(scale < 0)
        {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        } else
        {
            BigDecimal b = new BigDecimal(v);
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 4).toString();
        }
    }


    /**
      * @Description(功能描述): 比大小
      * @author(作者): lrfalse<wangliyou>
      * @date (开发日期): 2018/3/30 14:39
      **/
    public static int compareStr(String str1, String str2)
    {
        BigDecimal bg1 = new BigDecimal(str1);
        BigDecimal bg2 = new BigDecimal(str2);
        if(bg1.compareTo(bg2) == 1)
            return 1;
        return bg1.compareTo(bg2) != 0 ? -1 : 0;
    }



    /**
      * @Description(功能描述): 金额拆分
      * @author(作者): lrfalse<wangliyou>
      * @date (开发日期): 2018/3/30 14:39
      **/
    public static ArrayList divAmt(String tranAmt, String maxLimit)
    {
        ArrayList amtArray = new ArrayList();
        if(Double.parseDouble(tranAmt) <= Double.parseDouble(maxLimit))
        {
            amtArray.add(tranAmt);
            return amtArray;
        }
        if(tranAmt.contains("."))
            tranAmt = tranAmt.split("\\.")[0];
        Long time = Long.valueOf(Long.parseLong(tranAmt) / Long.parseLong(maxLimit));
        Long amt = Long.valueOf(Long.parseLong(tranAmt) % Long.parseLong(maxLimit));
        for(int i = 0; (long)i < time.longValue(); i++)
            amtArray.add(maxLimit);

        if(amt.longValue() != 0L)
            amtArray.add(amt);
        return amtArray;
    }

    public static void main(String args[])
    {
		System.out.println(sub("10.1456465","5.98651","3.154162"));
		System.out.println(add("0.03","0.5451"));
		System.out.println(compareStr("1","2"));
    }
}

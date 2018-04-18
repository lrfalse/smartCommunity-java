package com.tools.from;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @Description:sql批量插入
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-12 14:14:14
 */
public class SqlProvider {

    public String insetData(Map<String, Object> map) {
        //由Mapper传入的List在SQL构造类中将会包装在一个Map里，所以这里的参数是Map
        //key就是Mapper中@Param注解配置的名称
        List<T_community_info> t_community_infos = (List<T_community_info>) map.get("list");
        //构造SQL
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO linlihouse.t_community_info ");
        sb.append("(name, address, g_location, b_location, postcode, pcode, pname, citycode, cityname," +
                " adcode, adname, entr_location, photos_url, status, remark) ");
        sb.append("VALUES ");
        //构造SQL模板
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].name},#'{'list[{0}].address},#'{'list[{0}].g_location}," +
                "#'{'list[{0}].b_location},#'{'list[{0}].postcode},#'{'list[{0}].pcode},#'{'list[{0}].pname},#'{'list[{0}].citycode},#'{'list[{0}].cityname},#'{'list[{0}].adcode}," +
                "#'{'list[{0}].adname},#'{'list[{0}].entr_location},#'{'list[{0}].photos_url},#'{'list[{0}].status},#'{'list[{0}].remark})");
        for (int i = 0; i < t_community_infos.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < t_community_infos.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();

    }
}

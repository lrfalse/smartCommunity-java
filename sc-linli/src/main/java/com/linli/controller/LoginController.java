package com.linli.controller;

import com.alibaba.fastjson.JSON;
import com.commons.utils.CoordinatesConvert;
import com.linli.dao.DataImport;
import com.linli.form.Demo;
import com.linli.form.Pois;
import com.linli.form.T_community_info;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.commons.utils.CoordinatesConvert.*;

/**
 * @Description:登录注册
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-10 09:9:44
 */
@RestController
public class LoginController {


    @Autowired
    DataImport dataImport;
    /**
     * @Description:
     * @Param:
     * @create:2018-04-10 09:9:44
     * @Return:
     */
   OkHttpClient client = new OkHttpClient();
   String url = "http://restapi.amap.com/v3/place/text?key=264d80231fd6ab84d409310b74b6ad98&keywords=学府花园&city=永川区&citylimit=true&children=5&offset=24&page=1&extensions=all";
    @GetMapping(value = "/XXOO")
    public String run() {
        //String[] arr={"渝中区","大渡口区","江北区","沙坪坝区","九龙坡区","南岸区","北碚区","渝北区","巴南区","涪陵区","綦江区","大足区","长寿区","江津区","合川区","永川区","南川区","璧山区","铜梁区","潼南区","荣昌区","万州区","梁平县","城口县","丰都县","垫江县","忠县","开县","云阳县","奉节县","巫山县","巫溪县","黔江区","武隆县","石柱土家族自治县","秀山土家族苗族自治县","酉阳土家族苗族自治县","彭水苗族土家族自治县"};
            for (int i = 1; i <=4; i++) {
                String s = "page=" + i;
                String replace = url.replace("page=1", s);
                Request request = new Request.Builder().url(replace).build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String str = response.body().string();
                    Demo demo = JSON.parseObject(str, Demo.class);
                    List<Pois> pois = demo.getPois();
                    List<T_community_info> list = getList(pois);
                    List<String> query = dataImport.query();
                    Iterator<T_community_info> t_Iterator = list.iterator();
                    while (t_Iterator.hasNext()) {
                        String t_info = t_Iterator.next().getG_location();
                        for (String st : query) {
                            if (t_info == null) {
                            } else {
                                if (st.equals(t_info)) {
                                    t_Iterator.remove();
                                }
                            }

                        }
                    }
                    if (list.size() != 0) {
                        dataImport.batchSave(list);
                    } else {
                        //return "没有新小区";
                    }
                    //return "成功";
                } catch (Exception e) {
                    e.printStackTrace();
                }


        }

        return "成功";
    }


    public List<T_community_info> getList(List<Pois> list) {
        List<T_community_info> tlist = new ArrayList<>();
        for (Pois pois : list) {
            T_community_info t_community_info = new T_community_info();
            t_community_info.setName(pois.getName());
            t_community_info.setAddress(pois.getAddress());
            t_community_info.setG_location(pois.getLocation());
            t_community_info.setB_location(convert(pois.getLocation()));
            List<?> postcode = pois.getPostcode();
            if (postcode.isEmpty()) {
                t_community_info.setPostcode(Integer.valueOf("0"));
            }
            t_community_info.setPcode(Integer.valueOf(pois.getPcode()));
            t_community_info.setPname(pois.getPname());
            t_community_info.setCitycode(Integer.valueOf(pois.getCitycode()));
            t_community_info.setCityname(pois.getCityname());
            t_community_info.setAdcode(Integer.valueOf(pois.getAdcode()));
            t_community_info.setAdname(pois.getAdname());
            t_community_info.setEntr_location(pois.getEntr_location());
            t_community_info.setPhotos_url("");
            t_community_info.setStatus("0");
            t_community_info.setRemark("");
            tlist.add(t_community_info);
        }
        return tlist;
    }

    public String convert(String dou) {
        List list = new ArrayList();
        String[] split = dou.split(",");
        Double aDouble = Double.valueOf(split[0]);
        Double bDouble = Double.valueOf(split[1]);
        double[] doubles = CoordinatesConvert.gcj02tobd09(aDouble, bDouble);
        for (double d : doubles) {
            double v = CoordinatesConvert.retain6(d);
            list.add(v);
        }
        return list.toString().replace("[", "").replace("]", "");
    }
}

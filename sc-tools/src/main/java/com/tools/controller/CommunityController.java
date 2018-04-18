package com.tools.controller;

import com.alibaba.fastjson.JSON;
import com.commons.entity.CommunityEntity;
import com.commons.utils.lcationUtils;
import com.tools.from.Demo;
import com.tools.from.Pois;
import com.tools.mapper.CommunityMapper;
import com.tools.service.CommunityService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description:小区信息抓取
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-18 10:10:48
 */
@RestController
public class CommunityController {

    String url = "http://restapi.amap.com/v3/place/text?key=264d80231fd6ab84d409310b74b6ad98&keywords=和拓五米阳光&city=永川区&citylimit=true&children=5&offset=24&page=1&extensions=all";
    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private CommunityService communityService;
    /**
     * @Description:高德小区数据抓取
     * @Param:
     * @create:2018-04-10 09:9:44
     * @Return: 成功
     */
    OkHttpClient client = new OkHttpClient();

   @GetMapping("/community")
    public String addCommunity() {
        //String[] arr={"渝中区","大渡口区","江北区","沙坪坝区","九龙坡区","南岸区","北碚区","渝北区","巴南区","涪陵区","綦江区","大足区","长寿区","江津区","合川区","永川区","南川区","璧山区","铜梁区","潼南区","荣昌区","万州区","梁平县","城口县","丰都县","垫江县","忠县","开县","云阳县","奉节县","巫山县","巫溪县","黔江区","武隆县","石柱土家族自治县","秀山土家族苗族自治县","酉阳土家族苗族自治县","彭水苗族土家族自治县"};
        for (int i = 1; i <= 1; i++) {
            String s = "page=" + i;
            String replace = url.replace("page=1", s);
            Request request = new Request.Builder().url(replace).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String str = response.body().string();
                Demo demo = JSON.parseObject(str, Demo.class);
                List<CommunityEntity> list = getList(demo.getPois());
                List<String> query = communityMapper.queryLocation();
                Iterator<CommunityEntity> t_Iterator = list.iterator();
                Iterator<String> iterator = query.iterator();
                while (t_Iterator.hasNext()) {
                    String t_info = t_Iterator.next().getG_location();
                    while (iterator.hasNext()) {
                        String next = iterator.next();
                        if (next.equals(t_info)) {
                            t_Iterator.remove();
                        }
                    }
                }
                communityService.addbatch(list);
            } catch (Exception e) {
                e.printStackTrace();
                return "失败";
            }
        }
        return "成功";
    }

    /**
     * @Description(功能描述): 参数封装
     * @author(作者): feihong
     * @date (开发日期):2018/4/16
     * @Author: list对象
     **/
    public List<CommunityEntity> getList(List<Pois> list) {
        List<CommunityEntity> tlist = new ArrayList<>();
        for (Pois pois : list) {
            CommunityEntity communityEntity = new CommunityEntity();
            communityEntity.setName(pois.getName());
            communityEntity.setAddress(pois.getAddress());
            communityEntity.setG_location(pois.getLocation());
            communityEntity.setB_location(convert(pois.getLocation()));
            List<?> postcode = pois.getPostcode();
            if (postcode.isEmpty()) {
                communityEntity.setPostcode(Integer.valueOf("0"));
            }
            communityEntity.setPcode(Integer.valueOf(pois.getPcode()));
            communityEntity.setPname(pois.getPname());
            communityEntity.setCitycode(Integer.valueOf(pois.getCitycode()));
            communityEntity.setCityname(pois.getCityname());
            communityEntity.setAdcode(Integer.valueOf(pois.getAdcode()));
            communityEntity.setAdname(pois.getAdname());
            communityEntity.setEntr_location(pois.getEntr_location());
            communityEntity.setPhotos_url("");
            communityEntity.setStatus("0");
            communityEntity.setRemark("");
            tlist.add(communityEntity);
        }
        return tlist;
    }

    /**
     * @Description(功能描述): 坐标转换
     * @author(作者): feihong
     * @date (开发日期):2018/4/17
     **/
    public String convert(String dou) {
        List list = new ArrayList();
        String[] split = dou.split(",");
        Double aDouble = Double.valueOf(split[0]);
        Double bDouble = Double.valueOf(split[1]);
        double[] doubles = lcationUtils.gcj02tobd09(aDouble, bDouble);
        for (double d : doubles) {
            double v = lcationUtils.retain6(d);
            list.add(v);
        }
        return list.toString().replace("[", "").replace("]", "");
    }
}

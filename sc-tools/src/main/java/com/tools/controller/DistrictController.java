package com.tools.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.TestDto;
import com.commons.entity.DistrictEntity;
import com.commons.utils.lcationUtils;
import com.commons.utils.HttpClientUtil;
import com.tools.from.Demo;
import com.tools.from.Pois;
import com.tools.from.T_community_info;
import com.tools.mapper.CommunityMapper;
import com.tools.service.DistrictService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @Description(功能描述) :省市操作
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/2 22:06
 **/
@Controller
@RequestMapping("/")
public class DistrictController extends BaseApi {

    private String districtUrl = "http://restapi.amap.com/v3/config/district?key=264d80231fd6ab84d409310b74b6ad98";    //省市请求地址

    @Autowired
    private DistrictService districtService;

    @Autowired
    CommunityMapper communityMapper;

    /**
     * @Description(功能描述): 新增省市区
     * @author(作者): lrfalse<wangliyou>
     * @date (开发日期): 2018/4/3 18:17
     **/
    @SuppressWarnings("Duplicates")
    @ResponseBody
    @RequestMapping(value = "/addDistrict")
    public HttpResults addDistrict() throws Exception {
        JSONArray jsonProvince = getJSONArray(districtUrl);
        List<DistrictEntity> provinces = new ArrayList<>();                                        //省
        for (int i = 0; i < jsonProvince.size(); i++) {
            DistrictEntity district = getDistrict(jsonProvince.getJSONObject(i));
            district.setPcode(0);
            provinces.add(district);
        }
        districtService.addBatch(provinces);                    //保存省份信息
        List<DistrictEntity> citys = new ArrayList<>();                    //市
        for (DistrictEntity province : provinces) {
            String provinceUrl = districtUrl + "&keywords=" + province.getName() + "&subdistrict=1&filter" + province.getAdcode();//获取省市信息
            JSONArray jsonCity = getJSONArray(provinceUrl);
            for (int i = 0; i < jsonCity.size(); i++) {
                DistrictEntity city = getDistrict(jsonCity.getJSONObject(i));
                if (isOk(city.getAdcode())) {
                    city.setPcode(province.getAdcode());
                    citys.add(city);
                }
            }
        }
        districtService.addBatch(citys);                    //保存城市信息
        List<DistrictEntity> districts = new ArrayList<>();            //县
        for (DistrictEntity city : citys) {
            String cityUrl = districtUrl + "&keywords=" + city.getName() + "&subdistrict=1&filter=" + city.getAdcode();
            JSONArray jsonCity = getJSONArray(cityUrl);
            for (int i = 0; i < jsonCity.size(); i++) {
                DistrictEntity district = getDistrict(jsonCity.getJSONObject(i));
                if (isOk(district.getAdcode())) {
                    city.setPcode(city.getAdcode());
                    districts.add(district);
                }
            }
        }
        districtService.addBatch(districts);                    //保存县信息
        List<DistrictEntity> streets = new ArrayList<>();                //街道
        for (DistrictEntity district : districts) {
            String distUrl = districtUrl + "&keywords=" + district.getName() + "&subdistrict=1&filter=" + district.getAdcode() + "&offset=" + 99999;
            JSONArray jsonCity = getJSONArray(distUrl);
            for (int i = 0; i < jsonCity.size(); i++) {
                DistrictEntity street = getDistrict(jsonCity.getJSONObject(i));
                if (isOk(street.getAdcode())) {
                    street.setPcode(district.getAdcode());
                    streets.add(street);
                }
            }
        }
        districtService.addBatch(streets);                    //保存街道信息
        HttpResults test = getHttpResult(new TestDto());
        return test;
    }

    /**
     * @param requestUrl : 请求url地址
     * @Description(功能描述): 封装返回省市县街道对象
     * @author(作者): lrfalse<wangliyou>
     * @date (开发日期): 2018/4/16 17:17
     **/
    public JSONArray getJSONArray(String requestUrl) {
        String result = HttpClientUtil.getInstance().sendHttpGet(requestUrl);
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject jsonOne = jsonObject.getJSONArray("districts").getJSONObject(0);
        return jsonOne.getJSONArray("districts");
    }

    /**
     * @param object : 具体省市对象
     * @Description(功能描述): 封装返回地区对象
     * @author(作者): lrfalse<wangliyou>
     * @date (开发日期): 2018/4/16 15:55
     **/
    public DistrictEntity getDistrict(JSONObject object) {
        DistrictEntity district = new DistrictEntity();
        district.setAdcode(object.getInteger("adcode"));
        district.setLevel(object.getString("level"));
        district.setGLocation(object.getString("center"));
        district.setBLocation(lcationUtils.convert(object.getString("center")));
        district.setName(object.getString("name"));
        return district;
    }

    String itCode = "442000,441900,460400";

    public boolean isOk(int icode) {
        if (itCode.indexOf(icode + "") > -1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        DistrictController d = new DistrictController();
        System.out.println(d.isOk(442000));
    }


    /**
     * @Description:高德小区数据抓取
     * @Param:
     * @create:2018-04-10 09:9:44
     * @Return: 成功
     */
    OkHttpClient client = new OkHttpClient();
    String url = "http://restapi.amap.com/v3/place/text?key=264d80231fd6ab84d409310b74b6ad98&keywords=忆诚·城市铭人&city=永川区&citylimit=true&children=5&offset=24&page=1&extensions=all";
    @RequestMapping("/XXOO")
    @ResponseBody
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
                List<T_community_info> list = getList(demo.getPois());
                List<String> query = communityMapper.query();
                Iterator<T_community_info> t_Iterator = list.iterator();
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
                communityMapper.batchSave(list);
                return "成功";
            } catch (Exception e) {
                e.printStackTrace();

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

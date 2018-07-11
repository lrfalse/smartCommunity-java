//package com.tools.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.commons.entity.sys.CommunityInfoEntity;
//import com.commons.service.sys.CommunityInfoService;
//import com.commons.utils.lcationUtils;
//import com.tools.from.Demo;
//import com.tools.from.Pois;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * @Description:小区信息抓取
// * @Author:feihong
// * @Vsesion:v.10
// * @Create:2018-04-18 10:10:48
// */
//@RestController
//public class CommunityController {
//
//    String url = "http://restapi.amap.com/v3/place/text?key=264d80231fd6ab84d409310b74b6ad98&keywords=巨宇江南city=重庆市&citylimit=true&children=5&offset=24&page=1&extensions=all";
//	@Autowired
//	private CommunityInfoService communityService;
//
//
//    /**
//     * @Description:高德小区数据抓取
//     * @Param:
//     * @create:2018-04-10 09:9:44
//     * @Return: 成功
//     */
//    OkHttpClient client = new OkHttpClient();
//
//   @GetMapping("/community")
//    public String addCommunity() {
//       // String[] arr={"杭州市","宁波市","湖州市","嘉兴市","金华市","丽水市","衢州市","绍兴市","台州市","温州市","舟山市","武汉市","鄂州市","黄冈市","黄石市","荆门市","荆州市","十堰市","随州市","襄阳市","咸宁市","孝感市","宜昌市","恩施土家族苗族自治州","天门市","潜江市","仙桃市","神农架林区"};
//            for (int i = 1; i <=100; i++) {
//                String s = "page=" + i;
//                String replace = url.replace("page=1", s);
//                Request request = new Request.Builder().url(replace).build();
//                Response response = null;
//                try {
//                    response = client.newCall(request).execute();
//                    String str = response.body().string();
//                    Demo demo = JSON.parseObject(str, Demo.class);
//                    List<CommunityInfoEntity> list = getList(demo.getPois());
//                    //int i1 = list.size() / 24;
//                List<String> query = communityService.queryLocation();
//                    Iterator<CommunityInfoEntity> t_Iterator = list.iterator();
//                    Iterator<String> iterator = query.iterator();
//                    while (t_Iterator.hasNext()) {
//                        String t_info = t_Iterator.next().getG_location();
//                        while (iterator.hasNext()) {
//                            String next = iterator.next();
//                            if (next.equals(t_info)) {
//                                t_Iterator.remove();
//                            }
//                        }
//                    }
//					communityService.batchSave(list);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    // return "失败";
//                }
//            }
//
//        return "成功";
//    }
//
//    /**
//     * @Description(功能描述): 参数封装
//     * @author(作者): feihong
//     * @date (开发日期):2018/4/16
//     * @Author: list对象
//     **/
//    public List<CommunityInfoEntity> getList(List<Pois> list) {
//        List<CommunityInfoEntity> tlist = new ArrayList<>();
//        for (Pois pois : list) {
//            CommunityInfoEntity communityEntity = new CommunityInfoEntity();
//            communityEntity.setName(pois.getName());
//            communityEntity.setAddress(pois.getAddress());
//            communityEntity.setG_location(pois.getLocation());
//            communityEntity.setB_location(convert(pois.getLocation()));
//            List<?> postcode = pois.getPostcode();
//            if (postcode.isEmpty()) {
//                communityEntity.setPostcode(Integer.valueOf("0"));
//            }
//            communityEntity.setPcode(Integer.valueOf(pois.getPcode()));
//            communityEntity.setPname(pois.getPname());
//            communityEntity.setCityCode(Integer.valueOf(pois.getCitycode()));
//            communityEntity.setCityName(pois.getCityname());
//            communityEntity.setAdcode(Integer.valueOf(pois.getAdcode()));
//            communityEntity.setAdname(pois.getAdname());
//            communityEntity.setEntr_location(pois.getEntr_location());
//            communityEntity.setStatus("0");
//            communityEntity.setRemark("");
//            tlist.add(communityEntity);
//        }
//        return tlist;
//    }
//
//    /**
//     * @Description(功能描述): 坐标转换
//     * @author(作者): feihong
//     * @date (开发日期):2018/4/17
//     **/
//    public String convert(String dou) {
//        List list = new ArrayList();
//        String[] split = dou.split(",");
//        Double aDouble = Double.valueOf(split[0]);
//        Double bDouble = Double.valueOf(split[1]);
//        double[] doubles = lcationUtils.gcj02tobd09(aDouble, bDouble);
//        for (double d : doubles) {
//            double v = lcationUtils.retain6(d);
//            list.add(v);
//        }
//        return list.toString().replace("[", "").replace("]", "");
//    }
//
//    String[] arr={"万达华府","青年根据地","五米阳光","五洋蜂尚" ,"宇界维诗卡","奥兰半岛",
//             "橄榄郡" ,"米兰阳光" ,"金科中央公园城" ,"兴龙苑","君临棠城","恒大翡翠华庭",
//              "竹映三清" ,"凤凰育才" ,"龙洲锦绣花园","AC世纪城","润锦御珑山","江鸿枫侨郡",
//            "天地豪庭","豪禧银山源","凰城华府","竣祥红河枫景","荷塘月色","万紫苑","昌龙阳光尚城","",
//            "中山小区","时代国际","盛世棠城","名豪小区","桓大泰晤士清晨","金科阳光小镇","森望滟澜湖",
//            "沁馨苑","汇悦悦峰","渝西印象","金色大地","帝琴花园","益多苑","华茂国际中心",
//            "桓大郦晶城","晟凡兴龙湖一号","金剑小区","润智至尊华庭","永川世纪阳光","永奥巴塞罗那",
//            "吉发小区","永川协信中心","月源山水","永川浦江上海城","昕晖香缇时光","奇星花园","红河苑",
//            "骑龙圆","山水林涧","观南城","鸿翔康桥生活公园","乐信凤凰郡","金科公园王府","和顺人家",
//            "仁豪星城","嘉和圣托里尼","巨宇江南","棠城佳苑","水木年华","永川阳光花园","天之韵花园",
//            "置铖御府","协信世外桃源","百年华庭","永川碧桂园","永川碧桂园","乐华馨城国际","奥韵天居八号",
//            "长岛汇","万达华城","万达华城","嘉和香水湾","海棠苑","东科兰乔圣菲",
//            "文海国际天街","昌州城市花园","朝野水晶城","天秀锦地","学府美墅","竣尊御景国际","利安凰城御府",
//            "康安尚都","伟豪创世纪","善群天秀龙湾","新城丽苑","协润凤凰世纪城","和拓五米阳光","汇龙所有阳光",
//            "忆诚城市铭人","昕晖香缇漫城","东方文都","凤凰城","荣华名都","望城公园","金港明珠",
//            "金鑫山庄B区","亨通家园","石油路胜保小区","港都花园","永川汽摩机电城","永川安居工程","永川永荣小区",
//            "豪华佳苑","适口香居","永川教委家属院","居家花园","乾丰紫园","科融苑","帝都红旗广场","新东花苑",
//            "万都苑","宇界尚城","雄丰家苑","雄丰家苑","中环明珠","商贸城国际公寓","桓大中央华府",
//            "萱花学苑","昌州小区","松竹小区","官井小区","润智至尊华庭","东城上景","三华博雅苑",
//            "新胜小区","远铭大厦","石油汇碧苑小区","萱花小区","地税新村","华越小区","金灿望城名都",
//            "康怡棠新天地","俊豪中央大街(二期)","东门教育村","威尼斯蓝湾","豪都佳苑","水韵书香",
//            "气矿小区","幸福花园","永川市政家属院","永祥小区","澳滨郦舍","美馨花园","永川煤苑小区",
//            "天籁城","永能汇龙花园","文博书香","永川清华苑","茶叶公司家属院","盛世棠城",
//            "三正小区","石油路老小区","三圣公寓","探花庭园","永川财政局家属院","伟豪学林锦园",
//            "学府花园","禹泊苑","圣望大厦","佳和丽苑","福林小区","中河小区","东科家园","欣馨花苑",
//            "康居小区","银博佳苑","回龙苑","石油丽景小区","三华苑",
//            "翠苑","康达大厦","永青花园","风华楼","港九香山屿","明富小区","龙海锦绣花园","植物苑安置房",
//            "禄川箐华园","禄川郦园","萱花佳苑","创新花园","凤凰雅苑","俊佳花园","棠麓源","凤凰丽苑",
//            "东科名都","海棠世家","萱花映象","岭秀高地","腾龙苑","风情100国际公寓","桓大金和茗居",
//            "永川龙城国际","民政小区","奥韵阳光雅筑","凤竹苑","国瑞嘉苑","五洋都市庭园","同心苑",
//            "嘉信花园","紫光化工青年公寓","柳河康居","圣地豪庭","易泰苑","重印六厂小区","时代公寓",
//            "新华书苑","绿韵堡","俊豪中央大街(一期)","韵竹苑","绿野香洲","川粮花园","西城上元",
//            "禄川廊桥水岸","萱花苑","金利达花园","金剑香樟水岸","金叶小区","玉屏锦苑"};
//
//    @RequestMapping("screen")
//    public String screen(){
//		CommunityInfoEntity communityEntity=new CommunityInfoEntity();
//		communityEntity.setAdname("永川区");
//        List<CommunityInfoEntity> query = communityService.query(communityEntity);
//        List<String> strings = Arrays.asList(arr);
//        System.out.println(strings+"");
//        System.out.println(strings.size());
//        return "";
//    }
//}

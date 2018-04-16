package com.tools.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.TestDto;
import com.commons.entity.District;
import com.commons.utils.CoordinatesConvert;
import com.commons.utils.HttpClientUtil;
import com.commons.utils.RandomUtil;
import com.tools.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description(功能描述) :测试
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/2 22:06
 **/
@Controller
@RequestMapping("/")
public class DistrictController extends BaseApi{

	private String districtUrl="http://restapi.amap.com/v3/config/district?key=264d80231fd6ab84d409310b74b6ad98";	//省市请求地址

	@Autowired
	private DistrictService districtService;
	/**
	  * @Description(功能描述): 新增省市区
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 18:17
	  **/
	@SuppressWarnings("Duplicates")
	@ResponseBody
	@RequestMapping(value = "/addDistrict")
	public HttpResults addDistrict() throws Exception {
		String result= HttpClientUtil.getInstance().sendHttpGet(districtUrl);
		JSONObject jsonObject =JSONObject.parseObject(result);
		JSONObject jsonCountry=jsonObject.getJSONArray("districts").getJSONObject(0);	//国家
		JSONArray jsonProvince= jsonCountry.getJSONArray("districts");						//省
		List<District> provinces=new ArrayList<>();
		for(int i=0;i<jsonProvince.size();i++){
			District district=getDistrict(jsonProvince.getJSONObject(i));
			district.setPcode(0);
			provinces.add(district);
		}
		districtService.addBatch(provinces);					//保存省份信息
		List<District> citys=new ArrayList<>();					//市
		for(District province:provinces){
			String provinceUrl=districtUrl+"&keywords="+province.getName()+"&subdistrict=1&filter"+province.getAdcode();//获取省市信息
			JSONArray jsonCity= getJSONArray(provinceUrl);
			for(int i=0;i<jsonCity.size();i++){
				District city=getDistrict(jsonCity.getJSONObject(i));
				if(isOk(city.getAdcode())){
					city.setPcode(province.getAdcode());
					citys.add(city);
				}
			}
		}
		districtService.addBatch(citys);					//保存城市信息
		List<District> districts=new ArrayList<>();			//县
		for(District city:citys){
			String cityUrl=districtUrl+"&keywords="+city.getName()+"&subdistrict=1&filter="+city.getAdcode();
			JSONArray jsonCity= getJSONArray(cityUrl);
			for(int i=0;i<jsonCity.size();i++){
				District district=getDistrict(jsonCity.getJSONObject(i));
				if(isOk(district.getAdcode())){
					city.setPcode(city.getAdcode());
					districts.add(district);
				}
			}
		}
		districtService.addBatch(districts);					//保存县信息
		List<District> streets=new ArrayList<>();				//街道
		for(District district:districts){
			String distUrl=districtUrl+"&keywords="+district.getName()+"&subdistrict=1&filter="+district.getAdcode()+"&offset="+99999;
			JSONArray jsonCity=getJSONArray(distUrl);
			for(int i=0;i<jsonCity.size();i++){
				District street=getDistrict(jsonCity.getJSONObject(i));
				if(isOk(street.getAdcode())){
					street.setPcode(district.getAdcode());
					streets.add(street);
				}
			}
		}
		districtService.addBatch(streets);					//保存街道信息
		HttpResults test=getHttpResult(new TestDto());
		return test;
	}

	/**
	  * @Description(功能描述): 封装返回省市县街道对象
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/16 17:17
	 * @param requestUrl : 请求url地址
	  **/
	public  JSONArray getJSONArray(String requestUrl){
		String result= HttpClientUtil.getInstance().sendHttpGet(requestUrl);
		JSONObject jsonObject =JSONObject.parseObject(result);
		JSONObject jsonOne=jsonObject.getJSONArray("districts").getJSONObject(0);
		return jsonOne.getJSONArray("districts");
	}
	/**
	  * @Description(功能描述): 封装返回地区对象
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/16 15:55
	 * @param object : 具体省市对象
	  **/
	public District getDistrict(JSONObject object){
		District district=new District();
		district.setAdcode(object.getInteger("adcode"));
		district.setLevel(object.getString("level"));
		district.setGLocation(object.getString("center"));
		district.setBLocation(CoordinatesConvert.convert(object.getString("center")));
		district.setName(object.getString("name"));
		return district;
	}

	String itCode="442000,441900,460400";
	public boolean isOk(int icode){
		if(itCode.indexOf(icode+"")>-1){
			return false;
		}
		return true;
	}
	public static void main(String[] args){
		DistrictController d=new DistrictController();
	    System.out.println(d.isOk(442000));
	}
}

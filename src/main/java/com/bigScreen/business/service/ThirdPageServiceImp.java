package com.bigScreen.business.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bigScreen.business.dao.ClassMapper;
import com.bigScreen.business.dao.ThirdPageMapper;
import com.bigScreen.business.entity.ClassEntity;
import com.bigScreen.business.entity.WeatherEntity;
import com.bigScreen.business.model.ThirdModel;
import com.bigScreen.business.util.DateUtill;
import com.bigScreen.business.util.HttpClient;
import com.bigScreen.business.util.JSONUtil;
import com.bigScreen.business.util.Utility;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ThirdPageServiceImp implements ThirdPageService{

	private static final Logger logger = Logger.getLogger(ThirdPageServiceImp.class); 
	
	@Autowired
	private ThirdPageMapper thirdPageMapper;

	private static String[] strArray = {"供应链与支撑体系","技术体系","服务体系","营销体系"};
	
	
	
	
	
	/**
	 * 获取系统级别参训人数 
	 */
	public List<ThirdModel> getSumBySystem(){
		return thirdPageMapper.getSumBySystem();
	}
	
	/**
	 * 获取系统级别12月内参训人次
	 */
	public List<ThirdModel> getPersonSumBySystemAndDate(String startDate,String endDate){
		Map<String, Object>map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);

		return thirdPageMapper.getPersonSumBySystemAndDate(map);
	}
	
	/**
	 * 获取12个月内所有部门的参训人次
	 */
	public List<ThirdModel> getDepGroupByMonth(String startDate,String endDate){
		Map<String, Object>map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);

		return thirdPageMapper.getDepGroupByMonth(map);
	}

	

	@Override
	public Object getThirdPageData() {
		Map<String,Object>map = new HashMap<>();
		String endDate = DateUtill.getCurrentYearAndMonth();
		String startDate = DateUtill.getLast12MonthsByCurrentDate();
		
		List<ThirdModel>personSumBySystemAndDateList = getPersonSumBySystemAndDate(startDate, endDate);
		List<ThirdModel>personSum = getSumBySystem();
		
		NumberFormat percent = NumberFormat.getPercentInstance();
		percent.setMaximumFractionDigits(2);
		for (ThirdModel thirdSum : personSum) {
			for (ThirdModel third : personSumBySystemAndDateList) {
				if(thirdSum.getDepName().equals(third.getDepName())){
					BigDecimal a = BigDecimal.valueOf(third.getSumNum()).divide(BigDecimal.valueOf(thirdSum.getSumNum()),2,RoundingMode.HALF_UP);
					String value = percent.format(a.doubleValue());
					thirdSum.setPersent(value);
					break;
				}
			}
		}
		
		List<ThirdModel> deptList= getDepGroupByMonth(startDate, endDate);
		String companyCount = thirdPageMapper.getEmployeeCount();//公司总人数
		map.put("companyCount",companyCount);
		String trainCount = thirdPageMapper.getTrainingCount();// 参训人数
		map.put("trainCount",trainCount);
		String newJoinCount  = thirdPageMapper.getNewJoinCount();//新员工培训人数
		map.put("newJoinCount",newJoinCount);

		Map trainMap = new HashMap();
		List<Map> trainList = thirdPageMapper.getAllTrain();
		List<Map> employeeList = thirdPageMapper.getAllEmployee();
		for (int i = 0; i < trainList.size(); i++) {
			Map tmap = trainList.get(i);
			if (tmap.get("sys") != null){
				trainMap.put(tmap.get("sys"), tmap.get("count"));
			}
		}
		List<String> sysList = new ArrayList();
		List<String> proportionList = new ArrayList();
		for (int i = 0; i < employeeList.size(); i++) {
			Map emap = employeeList.get(i);
			String xname = String.valueOf(emap.get("sys"));
			Integer employeeC = Integer.valueOf(String.valueOf(emap.get("count")));
			Integer trainC = Integer.valueOf(String.valueOf(trainMap.get(xname)));
			sysList.add(xname);
			proportionList.add(txfloat(trainC,employeeC));
		}
//		右一图
		map.put("sysList",sysList);//x轴 体系
		map.put("proportionList",proportionList);// Y轴 比例

		map.put("yearList", DateUtill.getLast12Months(DateUtill.getCurrentYearAndMonth()));		
		map.put("deptList", deptList);
		map.put("SystemDataList", personSum);
		
		return map;
	}

	/**
	 * @param a 被除数
	 * @param b 除数
	 * @return 商
	 */
	public static String txfloat(int a,int b) {

		DecimalFormat df=new DecimalFormat("0.00");//设置保留位数

		return df.format((float)a/b);

	}

	

	private WeatherEntity analysisJson(String response,String cityName) throws Exception {
		
		WeatherEntity entity = new WeatherEntity();
		
		JSONObject jsonObject = JSONUtil.JsonToObject(response, JSONObject.class);
		JSONObject result =  jsonObject.getJSONObject("result");
		JSONArray HeWeather5 =  result.getJSONArray("HeWeather5");
		JSONObject HeWeather5Data  = (JSONObject) HeWeather5.get(0);
		
		JSONObject basic = HeWeather5Data.getJSONObject("basic");
		//get loc time
		JSONObject update = basic.getJSONObject("update");
		JSONArray daily_forecast = HeWeather5Data.getJSONArray("daily_forecast");
		JSONObject daily_forecast_Object = (JSONObject) daily_forecast.get(0);
		
		JSONObject cond=  daily_forecast_Object.getJSONObject("cond");
		JSONObject tmp =  daily_forecast_Object.getJSONObject("tmp");
		JSONObject wind =  daily_forecast_Object.getJSONObject("wind");
		
		entity.setWeather(cond.getString("txt_d"));
		entity.setUpdated_time(Utility.dateToEnglishDate(update.getString("loc")));
		entity.setTemperature(tmp.getString("max"));;
		entity.setCity(cityName);
		
		entity.setWind(wind.getString("spd"));
		
		
		return entity;
		
	}
}

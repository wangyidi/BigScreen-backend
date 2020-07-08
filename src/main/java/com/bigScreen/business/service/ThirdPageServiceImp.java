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
import com.bigScreen.business.model.ThirdDeptModel;
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
	public List<ThirdModel> getSumBySystem(String startDate,String endDate){
		Map<String, Object>map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return thirdPageMapper.getSumBySystem(map);
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
	 * 获取12个月内所有部门的参训人次 右2
	 */
	public List<ThirdModel> getDepGroupByMonth(String startDate,String endDate){
		Map<String, Object>map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		String[] yearList = DateUtill.getLast12Months(DateUtill.getCurrentYearAndMonth());
		 
		List<ThirdModel>List =thirdPageMapper.getDepGroupByMonth(map);
		for (ThirdModel thirdModel : List) {
			thirdModel.setRecordList(reBuild(thirdModel.getRecordList(),yearList));
		}
		return List;
	}

	
	private List<ThirdDeptModel> reBuild(List<ThirdDeptModel> recordList,String[] yearList){		
		List<ThirdDeptModel>newList = new ArrayList<>(12);
		for (String date : yearList) {
			ThirdDeptModel e = new ThirdDeptModel();
			e.setDate(date);
			e.setSumNum(0);
			newList.add(e);
		}
		
		for (ThirdDeptModel thirdDeptModel : recordList) {
			for (ThirdDeptModel thirdDept : newList) {
				if(thirdDeptModel.getDate().equals(thirdDept.getDate())){
					thirdDept.setSumNum(thirdDeptModel.getSumNum());
				}
			}
		}
		
		return newList;
	}
	
	

	@Override
	public Map<String,Object> getThirdPageData() {
		Map<String,Object>map = new HashMap<>();
		String endDate = DateUtill.getCurrentYearAndMonth();
		String startDate = DateUtill.getLast12MonthsByCurrentDate();
		map.put("startDate", startDate);
		map.put("endDate", endDate);

		Map<String,Object>currentYearMap = new HashMap<>();
		String endCurrentYearDate = DateUtill.getCurrentYearAndMonth();
		String startCurrentYearDate = DateUtill.getCurrentYearFirstMonth();
		currentYearMap.put("startDate", startCurrentYearDate);
		currentYearMap.put("endDate", endCurrentYearDate);

		//参训人次 左 4
		List<ThirdModel>participatingNumber = getPersonSumBySystemAndDate(startCurrentYearDate, endCurrentYearDate);
		//参训人数 左 4
		List<ThirdModel>participatingPersonTime = getSumBySystem(startCurrentYearDate, endCurrentYearDate);
		
		//参训人数比率 左 4
		List<ThirdModel>percentList = thirdPageMapper.getPercentList(currentYearMap);
		//参训人次比率 左 4
		List<ThirdModel>percentTimeList = thirdPageMapper.getPercentTimeList(currentYearMap);

		map.put("percentList", percentList);
		map.put("percentTimeList", percentTimeList);

		
		List<ThirdModel> deptList= getDepGroupByMonth(startDate, endDate); // 获取12个月内所有部门的参训人次 右2
		map.put("yearList", DateUtill.getLast12Months(DateUtill.getCurrentYearAndMonth()));
		map.put("deptList", deptList);

		map.put("participatingPersonTime", participatingPersonTime);
		map.put("participatingNumber", participatingNumber);
		//old
		map.put("SystemDataList", participatingNumber);

		return map;
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
		entity.setTemperature(tmp.getString("max"));
		entity.setCity(cityName);
		
		entity.setWind(wind.getString("spd"));
		
		
		return entity;
		
	}
}

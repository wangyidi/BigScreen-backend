package com.bigScreen.business.service;

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
import com.bigScreen.business.entity.ClassEntity;
import com.bigScreen.business.entity.WeatherEntity;
import com.bigScreen.business.util.HttpClient;
import com.bigScreen.business.util.JSONUtil;
import com.bigScreen.business.util.Utility;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ClassServiceImp implements ClassService{

	private static final Logger logger = Logger.getLogger(ClassServiceImp.class); 
	
	@Autowired
	private ClassMapper classMapper;


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




	@Override
	public List<ClassEntity> getAllClassData() {
		return classMapper.getAllClassData();
	}
	
}

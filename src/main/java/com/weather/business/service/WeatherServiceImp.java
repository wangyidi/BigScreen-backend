package com.weather.business.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.weather.business.entity.WeatherEntity;
import com.weather.business.util.HttpClient;
import com.weather.business.util.JSONUtil;
import com.weather.business.util.Utility;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class WeatherServiceImp implements WeatherService{

	private static final Logger logger = Logger.getLogger(WeatherServiceImp.class); 
	
	@Value("${weather.api.url}")
	private String URL;
	
	//record the weather detail
	// key city -> value JSON
	public static Map<String, String>static_cityMap =new HashMap<String,String>();
	
	@Value("${weather.array}")
	public String weatherToString;
	
	
	@Override
	public Map<String,Object> getCityListAndFirstCityDetail()throws Exception {
		
		logger.info(this.getClass().getSimpleName()+" getCityListAndFirstCityDetail start..");

		//record the city list and first city deatil
		Map<String, Object>map = new HashMap<String,Object>();
		
		List<String> cityList = new ArrayList<String>();
		WeatherEntity weatherEntity = new WeatherEntity();
		
		if (!StringUtils.isEmpty(weatherToString)) {
			
			String[] weatherArray = weatherToString.split(",");
			// string array to list
			cityList = Arrays.stream(weatherArray).collect(Collectors.toList());
			String cityName = cityList.get(0);
			
			// get from cache 
			if(static_cityMap.get(cityName)!=null) {
				weatherEntity = JSONUtil.JsonToObject(static_cityMap.get(cityName),WeatherEntity.class );
			}else {
				//get the first weather Entity  from API
				String response = HttpClient.doGet(URL.replace("$city$", cityName));
				
				weatherEntity = analysisJson(response,cityName);
				//record in static_cityMap
				static_cityMap.put(cityName, JSONUtil.ObjectToJson(weatherEntity));
			}
			
			map.put("cityList", cityList);
			map.put("weatherEntity", weatherEntity);
		}
		
		return  map;
	}

	@Override
	public String getWeatherByCityName(String cityName) throws Exception{
		//get from cache
		logger.info(this.getClass().getSimpleName()+" getWeacherByCityName start..");
		if(static_cityMap.get(cityName)!=null) {
			 return static_cityMap.get(cityName);
		}else {
			
			String response = HttpClient.doGet(URL.replace("$city$", cityName));
			WeatherEntity weatherEntity = analysisJson(response,cityName);
			
			String result = JSONUtil.ObjectToJson(weatherEntity);
			//record in static_cityMap
			static_cityMap.put(cityName, result);
			
			return result;
		}
		
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

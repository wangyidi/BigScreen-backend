package com.weather.business.schedule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.weather.business.entity.WeatherEntity;
import com.weather.business.service.WeatherServiceImp;
import com.weather.business.util.HttpClient;
import com.weather.business.util.JSONUtil;
import com.weather.business.util.Utility;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class TaskSchedule {
	
	Logger logger = Logger.getLogger(TaskSchedule.class);
	
	@Value("${weather.api.url}")
	private String URL;
	
	@Autowired
	private WeatherServiceImp serviceImp;

	@SuppressWarnings("static-access")
	@Scheduled(fixedDelay = 1000*60)
	public void work() throws Exception {
		
		logger.info("start task ...");
		String weather = serviceImp.weatherToString;
		
		List<String> list=Arrays.stream(weather.split(",")).collect(Collectors.toList());
		
		for (String cityName : list) {
			
			String response = HttpClient.doGet(URL.replace("$city$", cityName));
			WeatherEntity entity = analysisJson(response, cityName);
			serviceImp.static_cityMap.put(cityName, JSONUtil.ObjectToJson(entity));
		}

		logger.info("end task ...");
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

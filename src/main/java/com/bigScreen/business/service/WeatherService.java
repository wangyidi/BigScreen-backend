package com.bigScreen.business.service;

import java.util.Map;

public interface WeatherService {

	public Map<String,Object> getCityListAndFirstCityDetail() throws Exception;
	
	public String getWeatherByCityName(String cityName) throws Exception;
}

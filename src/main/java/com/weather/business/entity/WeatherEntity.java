package com.weather.business.entity;
/**
 * WeatherEntity
 * @author YiDiWang
 *
 */
public class WeatherEntity {
	
	private String city;	
	
	private String updated_time;
	
	private String weather;
	
	private String temperature;
	
	private String wind;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(String updated_time) {
		this.updated_time = updated_time;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	@Override
	public String toString() {
		return "WeatherEntity [city=" + city + ", updated_time=" + updated_time + ", weather=" + weather
				+ ", temperature=" + temperature + ", wind=" + wind + "]";
	}
	
	
}

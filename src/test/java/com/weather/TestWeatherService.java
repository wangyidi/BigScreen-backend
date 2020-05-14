package com.weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.weather.business.service.WeatherService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestWeatherService {
 
	@Autowired
	private WeatherService service;
	
	@Test
    public void testGetEntFileById() throws Exception{
		Assert.assertNotNull(service.getWeatherByCityName("beijing"));
	}
	
	@Test
    public void testetCityListAndFirstCityDetail() throws Exception{
		Assert.assertNotNull(service.getCityListAndFirstCityDetail());
	}
	
	@Test
	public void dateToEnStringDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE hh:mm aa",Locale.ENGLISH);
        String string = "2016-10-24 21:59:06";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        	String a = simpleDateFormat.format(sdf.parse(string));
			System.out.println(a);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
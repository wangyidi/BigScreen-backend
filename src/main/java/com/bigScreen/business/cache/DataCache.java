package com.bigScreen.business.cache;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bigScreen.business.controller.ScreenInfoController;
import com.bigScreen.business.entity.WeatherEntity;
import com.bigScreen.business.service.ScreenInfoService;
import com.bigScreen.business.service.SecondPageService;
import com.bigScreen.business.service.ThirdPageService;
import com.bigScreen.business.util.HttpClient;
import com.bigScreen.business.util.JSONUtil;



@Component
public class DataCache {
	
	
	private Logger logger = Logger.getLogger(DataCache.class);
	
	
	
	public static Map<String,Map<String,Object>>daliyDataMap = new HashMap<String, Map<String,Object>>();
	
}

package com.bigScreen.business.schedule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bigScreen.business.cache.DataCache;
import com.bigScreen.business.entity.WeatherEntity;
import com.bigScreen.business.service.ScreenInfoService;
import com.bigScreen.business.service.SecondPageService;
import com.bigScreen.business.service.ThirdPageService;
import com.bigScreen.business.service.WeatherServiceImp;
import com.bigScreen.business.util.HttpClient;
import com.bigScreen.business.util.JSONUtil;
import com.bigScreen.business.util.Utility;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class TaskSchedule {
	
	Logger logger = Logger.getLogger(TaskSchedule.class);
	
	@Autowired
	private SecondPageService secondPageService;
	@Autowired
	private ScreenInfoService screenInfoService;
	@Autowired
	private ThirdPageService thirdPageService;

	@SuppressWarnings("static-access")
	@Scheduled(fixedDelay = 1000*60*2)
	public void work() throws Exception {
		
		logger.info("start task ...");
		DataCache.daliyDataMap.put("thirdPageData", thirdPageService.getThirdPageData());
		DataCache.daliyDataMap.put("secondPageData", secondPageService.getSecondPageData());
//		DataCache.daliyDataMap.put("firstPageData", screenInfoService.getFirstPage());


		logger.info(DataCache.daliyDataMap);
	}
	
	
	
	
}

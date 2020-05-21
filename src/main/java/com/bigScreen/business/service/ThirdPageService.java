package com.bigScreen.business.service;

import java.util.List;
import java.util.Map;

import com.bigScreen.business.entity.ClassEntity;
import com.bigScreen.business.model.ThirdModel;

public interface ThirdPageService {
	
	
	Map<String,Object> getThirdPageData();

	List<ThirdModel> getSumBySystem();
	
	List<ThirdModel> getPersonSumBySystemAndDate(String startDate,String endDate);

	List<ThirdModel> getDepGroupByMonth(String startDate,String endDate);

}

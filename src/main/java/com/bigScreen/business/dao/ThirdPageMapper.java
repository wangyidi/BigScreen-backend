package com.bigScreen.business.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bigScreen.business.entity.ClassEntity;
import com.bigScreen.business.model.ThirdModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPageMapper {
    
	List<ThirdModel> getSumBySystem(Map parameterMap);
	
	List<ThirdModel> getPersonSumBySystemAndDate(Map parameterMap);

	List<ThirdModel> getDepGroupByMonth(Map parameterMap);

	String getEmployeeCount();

	String getTrainingCount();

	String getNewJoinCount();

	List<Map> getAllTrain();

	List<Map> getAllEmployee();

	List<Map> getClassMap();
	
	List<ThirdModel> getPercentList(Map parameterMap);

	List<ThirdModel> getPercentTimeList(Map parameterMap);

}
package com.bigScreen.business.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bigScreen.business.entity.ClassEntity;
import com.bigScreen.business.model.ThirdModel;

@Mapper
public interface ThirdPageMapper {
    
	List<ThirdModel> getSumBySystem();
	
	List<ThirdModel> getPersonSumBySystemAndDate(Map parameterMap);

	List<ThirdModel> getDepGroupByMonth(Map parameterMap);
	
}
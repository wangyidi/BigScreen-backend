package com.bigScreen.business.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bigScreen.business.entity.ClassEntity;

@Mapper
public interface ClassMapper {
    
	List<ClassEntity> getAllClassData();
}
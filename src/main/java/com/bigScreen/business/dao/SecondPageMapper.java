package com.bigScreen.business.dao;
import java.util.List;
import java.util.Map;

import com.bigScreen.business.model.SecondModel;
import org.apache.ibatis.annotations.Mapper;

import com.bigScreen.business.entity.ClassEntity;
import com.bigScreen.business.model.ThirdModel;

@Mapper
public interface SecondPageMapper {

    List<SecondModel> getCountryList();
    List<SecondModel> getClassNameByCity();
}
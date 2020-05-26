package com.bigScreen.business.dao;

import com.bigScreen.business.model.SecondModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecondPageMapper {

    List<SecondModel> getCountryList();
    List<SecondModel> getClassNameByCity();
}
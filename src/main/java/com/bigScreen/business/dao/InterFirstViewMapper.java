package com.bigScreen.business.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterFirstViewMapper {

    String getTrainingCountNow();

    String getTrainingSatisficition();

    String getWorkingHours();

    String getPeopleCount();

    String getLessonCount();

    String getClassCount();

    List<String>getLastYearTrainNumber(@Param("yearList") List yearList);

    List<String>getLastYearTrainCount(@Param("yearList") List yearList);

    List<String>getTrainingTimes(@Param("yearList") List yearList);

    List<String>getTrainingDays(@Param("yearList") List yearList);

}
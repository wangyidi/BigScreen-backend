package com.bigScreen.business.service;

import com.bigScreen.business.dao.InterFirstViewMapper;
import com.bigScreen.business.entity.FirstPageDto;
import com.bigScreen.business.util.DateUtill;
import com.bigScreen.business.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author:dxy
 * @date:2020/5/16
 * @description:
 */
@Service
public class ScreenInfoServiceImp implements ScreenInfoService {

    @Autowired
    private InterFirstViewMapper interFirstViewMapper;

    @Override
    public String  getFirstPage() {

        FirstPageDto firstPageDto = new FirstPageDto();

        String trainCount = interFirstViewMapper.getTrainingCountNow();
        firstPageDto.setTrainingCountNow(trainCount);// 实时参训人数

        firstPageDto.setSatisficition(interFirstViewMapper.getTrainingSatisficition());//满意度

        firstPageDto.setWorkingHours(interFirstViewMapper.getWorkingHours());//时长

        firstPageDto.setPeopleCount(interFirstViewMapper.getPeopleCount());//人数

        firstPageDto.setLessonCount(interFirstViewMapper.getLessonCount());//课程

        firstPageDto.setClassCount(interFirstViewMapper.getClassCount());//场次

        List<String> yearList= DateUtill.getLastTweentyMonths();
        firstPageDto.setMounthList(yearList);// echarts 图 X 轴

        List<String> lastYearTrainNumber = interFirstViewMapper.getLastYearTrainNumber(yearList); // 参训人数
        firstPageDto.setLastYearTrainNumber(lastYearTrainNumber);

        List<String> lastYearTrainCount = interFirstViewMapper.getLastYearTrainCount(yearList);//参训人次
        firstPageDto.setLastYearTrainCount(lastYearTrainCount);

        List<String> trainingTimes = interFirstViewMapper.getTrainingTimes(yearList);//场次
        firstPageDto.setTrainingTimes(trainingTimes);

        List<String> trainingDays = interFirstViewMapper.getTrainingDays(yearList);//培训天数
        firstPageDto.setTrainingDays(trainingDays);

        String result =JSONUtil.ObjectToJson(firstPageDto);
        return result;
    }
}

package com.bigScreen.business.service;

import com.bigScreen.business.dao.InterFirstViewMapper;
import com.bigScreen.business.util.DateUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object>  getFirstPage() {

        Map<String,Object> map = new HashMap<>();
//        FirstPageDto firstPageDto = new FirstPageDto();


        String trainCount = interFirstViewMapper.getTrainingCountNow();
//        firstPageDto.setTrainingCountNow(trainCount);
        map.put("trainingCountNow",trainCount);// 实时参训人次

//        firstPageDto.setSatisficition();//满意度
        map.put("satisficition",interFirstViewMapper.getTrainingSatisficition());//满意度

        map.put("workingHours",interFirstViewMapper.getWorkingHours());//时长
//        firstPageDto.setWorkingHours();//时长

        map.put("peopleCount",interFirstViewMapper.getPeopleCount());//人数
//        firstPageDto.setPeopleCount();//人数

        map.put("lessonCount",interFirstViewMapper.getLessonCount());//课程
//        firstPageDto.setLessonCount(interFirstViewMapper.getLessonCount());//课程

        map.put("classCount",interFirstViewMapper.getClassCount());//场次
//        firstPageDto.setClassCount(interFirstViewMapper.getClassCount());//场次

        List<String> yearList= DateUtill.getLastTweentyMonths();
//        firstPageDto.setMounthList(yearList);// echarts 图 X 轴
        map.put("mounthList",yearList);//echarts 图X轴

        List<String> lastYearTrainNumber = interFirstViewMapper.getLastYearTrainNumber(yearList);
//        firstPageDto.setLastYearTrainNumber(lastYearTrainNumber);
        map.put("lastYearTrainNumber",lastYearTrainNumber);// 参训人数

        List<String> lastYearTrainCount = interFirstViewMapper.getLastYearTrainCount(yearList);
        map.put("lastYearTrainCount",lastYearTrainCount);//参训人次
//        firstPageDto.setLastYearTrainCount(lastYearTrainCount);

        List<String> trainingTimes = interFirstViewMapper.getTrainingTimes(yearList);//场次
        map.put("trainingTimes",trainingTimes);
//        firstPageDto.setTrainingTimes(trainingTimes);

        List<String> trainingDays = interFirstViewMapper.getTrainingDays(yearList);//培训天数
        map.put("trainingDays",trainingDays);
//        firstPageDto.setTrainingDays(trainingDays);

//        String result =JSONUtil.ObjectToJson(firstPageDto);
        return map;
    }
}

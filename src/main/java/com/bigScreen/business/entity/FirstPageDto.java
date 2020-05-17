package com.bigScreen.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author:dxy
 * @date:2020/5/16
 * @description:
 */
public class FirstPageDto implements Serializable {

    /**
     *  序列化
     */
    private static final long serialVersionUID = 1L;

//    实时参训人数
    private String trainingCountNow;
//    满意度
    private String satisficition;
//    时长
    private String workingHours;
//    人数
    private String peopleCount;
//    课程
    private String lessonCount;
//    场次
    private String classCount;
//    月份
    private List<String> mounthList;
//    参训人数
    private List<String> lastYearTrainNumber;
//    参训人次
    private List<String> lastYearTrainCount;
//    培训场次
    private List<String> trainingTimes;
//    培训天数
    private List<String> trainingDays;


    public String getTrainingCountNow() {
        return trainingCountNow;
    }

    public void setTrainingCountNow(String trainingCountNow) {
        this.trainingCountNow = trainingCountNow;
    }

    public String getSatisficition() {
        return satisficition;
    }

    public void setSatisficition(String satisficition) {
        this.satisficition = satisficition;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(String peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(String lessonCount) {
        this.lessonCount = lessonCount;
    }

    public String getClassCount() {
        return classCount;
    }

    public void setClassCount(String classCount) {
        this.classCount = classCount;
    }

    public List<String> getMounthList() {
        return mounthList;
    }

    public void setMounthList(List<String> mounthList) {
        this.mounthList = mounthList;
    }

    public List<String> getLastYearTrainNumber() {
        return lastYearTrainNumber;
    }

    public void setLastYearTrainNumber(List<String> lastYearTrainNumber) {
        this.lastYearTrainNumber = lastYearTrainNumber;
    }

    public List<String> getLastYearTrainCount() {
        return lastYearTrainCount;
    }

    public void setLastYearTrainCount(List<String> lastYearTrainCount) {
        this.lastYearTrainCount = lastYearTrainCount;
    }

    public List<String> getTrainingTimes() {
        return trainingTimes;
    }

    public void setTrainingTimes(List<String> trainingTimes) {
        this.trainingTimes = trainingTimes;
    }

    public List<String> getTrainingDays() {
        return trainingDays;
    }

    public void setTrainingDays(List<String> trainingDays) {
        this.trainingDays = trainingDays;
    }
}

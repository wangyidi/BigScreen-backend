package com.bigScreen.business.model;

import java.io.Serializable;
import java.util.List;

public class SecondModel implements Serializable {


    private String country;

    private String province;

    private List<ClassModel> classNameList;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<ClassModel> getClassNameList() {
        return classNameList;
    }

    public void setClassNameList(List<ClassModel> classNameList) {
        this.classNameList = classNameList;
    }

    @Override
    public String toString() {
        return "SecondModel{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", classNameList=" + classNameList +
                '}';
    }



}

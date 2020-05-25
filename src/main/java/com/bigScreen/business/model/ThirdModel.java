package com.bigScreen.business.model;

import java.io.Serializable;
import java.util.List;

public class ThirdModel implements Serializable{

	private static final long serialVersionUID = -3099577752863596127L;

	private Integer sumNum;
	
	private String depName;
	
	private String persent;
	
	private String system;

	private String date;

	List<ThirdDeptModel>recordList;
	
	private Integer personTime;
	
	private Integer number;

	private Double percent;

	
	
	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public Integer getPersonTime() {
		return personTime;
	}

	public void setPersonTime(Integer personTime) {
		this.personTime = personTime;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public List<ThirdDeptModel> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<ThirdDeptModel> recordList) {
		this.recordList = recordList;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public Integer getSumNum() {
		return sumNum;
	}

	public void setSumNum(Integer sumNum) {
		this.sumNum = sumNum;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getPersent() {
		return persent;
	}

	public void setPersent(String persent) {
		this.persent = persent;
	}

	@Override
	public String toString() {
		return "ThirdModel [sumNum=" + sumNum + ", depName=" + depName + ", persent=" + persent + "]";
	}
	
	
	
	
	
}

package com.bigScreen.business.model;

import java.io.Serializable;

public class ThirdModel implements Serializable{

	private static final long serialVersionUID = -3099577752863596127L;

	private Integer sumNum;
	
	private String depName;
	
	private String persent;
	
	private String system;

	private String date;

	
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

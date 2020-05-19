package com.bigScreen.business.model;

import java.io.Serializable;

public class ThirdDeptModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5151231463859228116L;


	private Integer sumNum;


	private String date;


	public Integer getSumNum() {
		return sumNum;
	}


	public void setSumNum(Integer sumNum) {
		this.sumNum = sumNum;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
	
	

}

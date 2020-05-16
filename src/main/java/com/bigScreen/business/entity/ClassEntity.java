package com.bigScreen.business.entity;

import java.io.Serializable;

public class ClassEntity implements Serializable{

	/**
	 *  序列化
	 */
	private static final long serialVersionUID = -5173371417848837860L;

	private String class_id;
	
	private String class_no;
	
	private String system;
	
	private String classification;
	
	private String duration;
	
	//工作时间
	private String working_hours;
	
	//地址
	private String address;
	
	//满意度
	private Double satisfiction;

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getClass_no() {
		return class_no;
	}

	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getWorking_hours() {
		return working_hours;
	}

	public void setWorking_hours(String working_hours) {
		this.working_hours = working_hours;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getSatisfiction() {
		return satisfiction;
	}

	public void setSatisfication(Double satisfiction) {
		this.satisfiction = satisfiction;
	}

	@Override
	public String toString() {
		return "ClassEntity [class_id=" + class_id + ", class_no=" + class_no + ", system=" + system
				+ ", classification=" + classification + ", duration=" + duration + ", working_hours=" + working_hours
				+ ", address=" + address + ", satisfiction=" + satisfiction + "]";
	}

	
}

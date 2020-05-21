package com.bigScreen.business.entity;

import java.io.Serializable;

public class EmployeeEntity implements Serializable{


	private static final long serialVersionUID = 3638895771532801520L;

	private String employee_no;

	private String name;

	private String dept;

	private String system;

	public String getEmployee_no() {
		return employee_no;
	}

	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept_name() {
		return dept;
	}

	public void setDept_name(String dept) {
		this.dept = dept;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	@Override
	public String toString() {
		return "Employee [employee_no=" + employee_no + ", name=" + name + ", dept=" + dept + ", system="
				+ system + "]";
	}




}

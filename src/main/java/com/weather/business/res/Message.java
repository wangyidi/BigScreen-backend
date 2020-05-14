package com.weather.business.res;


public class Message {
	
	private int code = 200;

	private String msg;
	
	private String more_info;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMore_info() {
		return more_info;
	}

	public void setMore_info(String more_info) {
		this.more_info = more_info;
	}

	public Message(String msg, String more_info) {
		super();
		this.msg = msg;
		this.more_info = more_info;
	}

	public Message() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Message(int code, String msg, String more_info) {
		super();
		this.code = code;
		this.msg = msg;
		this.more_info = more_info;
	}

	
	
	
}

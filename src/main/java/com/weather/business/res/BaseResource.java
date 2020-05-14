package com.weather.business.res;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseResource {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseResource.class);
	
	
	protected Message message(int code,String msg,String parameters,HttpServletRequest request, HttpServletResponse response,Exception e){
		
		Message message = new Message(code,msg,request.getRequestURL().toString());
		if(code!=200) {
			response.setStatus(code);
		}
		if(null != e) {
			logger.error("Request URL:" + request.getRequestURL() +",Parameters: "+parameters.toString()+ " , Message:" + msg, e);
		} else {
			logger.info("Request URL:" + request.getRequestURL()  +",Parameters: "+parameters.toString()+ " , Message:" + msg);
		}
		return message;
	}

}

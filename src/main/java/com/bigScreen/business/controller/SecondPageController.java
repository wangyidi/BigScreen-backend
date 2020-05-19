package com.bigScreen.business.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bigScreen.business.entity.ClassEntity;
import com.bigScreen.business.model.ThirdModel;
import com.bigScreen.business.res.BaseResource;
import com.bigScreen.business.service.ClassService;
import com.bigScreen.business.service.SecondPageService;
import com.bigScreen.business.service.ThirdPageService;
import com.bigScreen.business.service.WeatherService;

@Controller
@RequestMapping(value="/")
public class SecondPageController extends BaseResource{

private static final Logger logger = Logger.getLogger(SecondPageController.class);
	
	@Autowired
	private SecondPageService secondPageService;
	
	@RequestMapping(value="/getSecondPageData",method=RequestMethod.POST)
	@ResponseBody
	public Object getSecondPageData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			logger.info("getSecondPageData ..start");
			Object object =  secondPageService.getSecondPageData();
			return object;
		 } catch (Exception e) {
	         logger.error("getThirdPageData exception:", e);
	         return message(500, e.getMessage(),"", request, response, e);
	     }
	}
	
	
}

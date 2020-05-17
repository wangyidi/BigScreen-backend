package com.bigScreen.business.controller;

import com.bigScreen.business.res.BaseResource;
import com.bigScreen.business.service.ScreenInfoService;
import com.bigScreen.business.service.WeatherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:dxy
 * @date:2020/5/16
 * @description:
 */
@Controller
@RequestMapping("info")
public class ScreenInfoController extends BaseResource {


    @Autowired
    private  ScreenInfoService screenInfoService;

    private static final Logger logger = Logger.getLogger(WeatherService.class);

    @RequestMapping("getFirstPage")
    @ResponseBody
    public Object getFirstPage(HttpServletRequest request, HttpServletResponse response){

        try {
            logger.info("ScreenInfoController ..start");
            String jsonObject = screenInfoService.getFirstPage();
            return jsonObject;
        } catch (Exception e) {
            logger.error("getWeatherListByCityName exception:", e);
            return message(500, e.getMessage(), "getFirstPage", request, response, e);

        }

    }



}

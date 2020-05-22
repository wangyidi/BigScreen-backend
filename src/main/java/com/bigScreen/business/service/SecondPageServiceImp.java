package com.bigScreen.business.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bigScreen.business.dao.InterFirstViewMapper;
import com.bigScreen.business.dao.SecondPageMapper;
import com.bigScreen.business.model.SecondModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bigScreen.business.dao.ClassMapper;
import com.bigScreen.business.dao.ThirdPageMapper;
import com.bigScreen.business.entity.ClassEntity;
import com.bigScreen.business.entity.WeatherEntity;
import com.bigScreen.business.model.ThirdModel;
import com.bigScreen.business.util.DateUtill;
import com.bigScreen.business.util.HttpClient;
import com.bigScreen.business.util.JSONUtil;
import com.bigScreen.business.util.Utility;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class SecondPageServiceImp implements SecondPageService{

	private static final Logger logger = Logger.getLogger(SecondPageServiceImp.class);

	@Autowired
	private ThirdPageMapper thirdPageMapper;

	@Autowired
	private InterFirstViewMapper interFirstViewMapper;

	@Autowired
	private SecondPageMapper secondPageMapper;

	@Override
	public Map<String, Object> getSecondPageData() {
		Map<String,Object>map = new HashMap<>();
		String companyCount = thirdPageMapper.getEmployeeCount();//公司总人数
		map.put("companyCount",companyCount);
		String trainCount = thirdPageMapper.getTrainingCount();// 参训人数
		map.put("trainCount",trainCount);
		String newJoinCount  = thirdPageMapper.getNewJoinCount();//新员工培训人数
		map.put("newJoinCount",newJoinCount);

		Map trainMap = new HashMap();
		List<Map> trainList = thirdPageMapper.getAllTrain();
		List<Map> employeeList = thirdPageMapper.getAllEmployee();
		for (int i = 0; i < trainList.size(); i++) {
			Map tmap = trainList.get(i);
			if (tmap.get("sys") != null){
				trainMap.put(tmap.get("sys"), tmap.get("count"));
			}
		}
		List<String> sysList = new ArrayList();
		List<String> proportionList = new ArrayList();
		for (int i = 0; i < employeeList.size(); i++) {
			Map emap = employeeList.get(i);
			String xname = String.valueOf(emap.get("sys"));
			Integer employeeC = Integer.valueOf(String.valueOf(emap.get("count")));
			Integer trainC = Integer.valueOf(String.valueOf(trainMap.get(xname)));
			sysList.add(xname);
			proportionList.add(txfloat(trainC,employeeC));
		}
//		右一图
		map.put("sysList",sysList);//x轴 体系
		map.put("proportionList",proportionList);// Y轴 比例

//		右二图
		List<String> yearList= DateUtill.getLastTweentyMonths();
		map.put("yearList",yearList);// 年+月份


		List<String> trainScale = new ArrayList<>();
		List<String> lastYearTrainNumber = interFirstViewMapper.getLastYearTrainNumber(yearList); // 参训人数
		for (String countNumber : lastYearTrainNumber) {
			trainScale.add(txfloat(Integer.parseInt(countNumber),Integer.parseInt(companyCount)));
		}
		map.put("trainScale",trainScale); // 覆盖人次增长率


		List<Map> classList = thirdPageMapper.getClassMap();
		map.put("classList", classList);//课程

		List<SecondModel>countryList = secondPageMapper.getCountryList(); // 国外地图信息
		if(!countryList.stream().filter(m->m.getCountry().equals("中国")).findAny().isPresent()){
			SecondModel secondModel = new SecondModel();
			secondModel.setCountry("中国");
			countryList.add(secondModel);
		}
		map.put("countryList", countryList);
		List<SecondModel>calssNameList = secondPageMapper.getClassNameByCity(); // 课程名称信息
		map.put("calssNameList", calssNameList);

		return map;
	}

	/**
	 * @param a 被除数
	 * @param b 除数
	 * @return 商
	 */
	public static String txfloat(int a,int b) {

		DecimalFormat df=new DecimalFormat("0.00");//设置保留位数

		return df.format((float)a/b);

	}
}

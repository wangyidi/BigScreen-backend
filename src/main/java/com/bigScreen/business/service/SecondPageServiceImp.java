package com.bigScreen.business.service;

import com.bigScreen.business.dao.InterFirstViewMapper;
import com.bigScreen.business.dao.SecondPageMapper;
import com.bigScreen.business.dao.ThirdPageMapper;
import com.bigScreen.business.model.SecondModel;
import com.bigScreen.business.util.DateUtill;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		String trainCount = thirdPageMapper.getTrainingCount();// 参训人数覆盖率
		map.put("trainCount",Double.parseDouble(txfloat(Integer.parseInt(trainCount), Integer.parseInt(companyCount))) *100);
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

		List<Map<String,String>> sysList = new ArrayList();
		for (int i = 0; i < employeeList.size(); i++) {
			Map emap = employeeList.get(i);
			String xname = String.valueOf(emap.get("sys"));
			Integer employeeC = Integer.valueOf(String.valueOf(emap.get("count")));
			Integer trainC = Integer.valueOf(String.valueOf(trainMap.get(xname)));
			Map<String,String> tmp = new HashMap<>();
			tmp.put("name", xname);//体系名称
			tmp.put("value",Double.parseDouble(txfloat(trainC,employeeC)) * 100 + "");
			sysList.add(tmp);
		}


//		右一图
		map.put("sysList",sysList);

//		右二图
		List<String> yearList= DateUtill.getLastTweentyMonths();
		map.put("yearList",yearList);// 年+月份


		List<String> trainScale = new ArrayList<>();
		List<String> lastYearTrainNumber = interFirstViewMapper.getLastYearTrainNumber(yearList); // 参训人数
		for (String countNumber : lastYearTrainNumber) {
			trainScale.add(txfloat(Integer.parseInt(countNumber),Integer.parseInt(companyCount)));
		}
		map.put("trainScale",trainScale); // 参训人数增长率


		List<String> trainCountScale = new ArrayList<>();
		List<String> lastYearTrainCount = interFirstViewMapper.getLastYearTrainCount(yearList);// 参训人次
		for (String train : lastYearTrainCount) {
			trainCountScale.add(txfloat(Integer.parseInt(train),Integer.parseInt(companyCount)));
		}
		map.put("trainCountScale",trainCountScale);// 参训人次 增长率

		List<Map> classList = thirdPageMapper.getClassMap();
		map.put("classList", classList);//课程

		List<SecondModel>countryList = secondPageMapper.getCountryList(); // 国外地图信息
		if(!countryList.stream().filter(m->m.getCountry().equals("中国")).findAny().isPresent()){
			SecondModel secondModel = new SecondModel();
			secondModel.setCountry("中国");
			countryList.add(secondModel);
		}
		map.put("countryList", mapToJson(countryList)); 
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
	
private JSONObject mapToJson(List<SecondModel> countryList) {
		
		
		JSONObject world_map_dict = new JSONObject();
		JSONArray array =  new JSONArray();
		
		JSONObject fixvalue = new JSONObject();
		fixvalue.put("attack_count","1");
		
		JSONObject ob = new JSONObject();
		for (SecondModel secondModel : countryList) {
			ob.put(secondModel.getCountry(), fixvalue);
		}
		world_map_dict.put("world_map_dict", ob);

		
		return world_map_dict;
		
	}
}

package com.weather.business.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONObject;

public class JSONUtil {

	public static String ObjectToJson(Object object) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(object);
	}

	public static <T> T JsonToObject(String json, Class<T> clazz) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(json, clazz);
	}

	public static Map<String, Object> JsonToMaOp(JSONObject jsonObject) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		for (Object key : jsonObject.keySet()) {
			resMap.put(String.valueOf(key), jsonObject.get(key));
		}
		return resMap;
	}
	
	
}
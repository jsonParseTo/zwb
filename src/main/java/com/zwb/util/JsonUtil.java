package com.zwb.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	public static ObjectMapper mapper = new ObjectMapper();
	public static <K,V> String MapConvertToJson(Map<K,V> map) throws JsonProcessingException{
		String json = mapper.writeValueAsString(map);
		return json;
	}
	
	public static <K,V> Map<K,V> JsonConvertToMap(String json) throws IOException{
		Map<K,V> map = mapper.readValue(json, Map.class);
		return map;
	}
	
	public static void main(String[] args) throws Exception {
		Map<String ,String> map = new HashMap<String,String>();
		map.put("name", "lucia");
		map.put("role", "admin");
		String json = MapConvertToJson(map);
		System.out.println(json);
		
//		String json = "{\"role\":\"admin\",\"nam}";
//		Map<String ,String> map = JsonConvertToMap(json);
//		Set<String> set = map.keySet();
//		for(String key : set){
//			System.out.println("key : "+key+" , value : "+map.get(key));
//		}
	}
}

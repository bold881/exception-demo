package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JacksonJsonParser;

import java.util.*;

/**
 * @author daifeng
 */
public class ParamUtil {

    /**
     * JSON字符串转换为map
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> convertJsonStrToMap(String jsonStr){
        JacksonJsonParser jsonParser =  new JacksonJsonParser();
        Map<String, Object> objectMap = jsonParser.parseMap(jsonStr);
        Map<String, Object> retMap = new HashMap<>(10);
        findInHashMap(retMap, (LinkedHashMap<String, Object>) objectMap);
        return retMap;
    }

    /**
     * Map中的JSON对象转换为Map
     * @param retMap
     * @param targetMap
     */
    public static void findInHashMap(Map<String, Object> retMap, LinkedHashMap<String, Object> targetMap) {
        targetMap.forEach((k, v) -> {
            retMap.put(k, v);
            if(v.getClass().equals(LinkedHashMap.class)) {
                findInHashMap(retMap, (LinkedHashMap<String, Object>) v);
            } else if(v.getClass().equals(ArrayList.class)) {
                findInArrayList(retMap, (ArrayList<Object>) v);
            }
        });
    }

    /**
     * List中的JSON对象转换为Map
     * @param retMap
     * @param arrayList
     */
    public static void findInArrayList(Map<String, Object> retMap, ArrayList<Object> arrayList) {
        arrayList.forEach(s -> {
            if(s.getClass().equals(LinkedHashMap.class)) {
                findInHashMap(retMap, (LinkedHashMap<String, Object>) s);
            } else if(s.getClass().equals(ArrayList.class)) {
                findInArrayList(retMap, (ArrayList<Object>) s);
            }
        });
    }

}

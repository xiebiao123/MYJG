package com.soshow.ssi.test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Java8Test {
    
	/**
	 * 对map进行排序
	 * @param args
	 */
    public static void main(String[] args) {
    	Map<String, String> map = new HashMap<>();
    	
    	map.put("one", "1");
    	map.put("two", "2");
    	map.put("five", "5");
    	map.put("three", "3");
    	map.put("four", "4");
    	map.put("ta", "4");
    	
        Map<String, String> result = new LinkedHashMap<>();
    	map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(x->result.put(x.getKey(), x.getValue()));
    	//map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).forEach(x->result.put(x.getKey(), x.getValue()));
    	
    	System.out.println(result);
	}
}
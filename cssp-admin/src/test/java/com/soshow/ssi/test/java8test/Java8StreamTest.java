package com.soshow.ssi.test.java8test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Java8StreamTest {
    
    /**
	 * 对map进行排序
	 * @param args
	 */
    @Test
    public void testDistinct() {
    	List<String> list = new ArrayList<>();
    	list.add("1");
    	list.add("2");
    	list.add("1");
    	list.add("2");
    	System.out.println(list);
//    	list = list.stream().filter(x -> !x.equals("1")).distinct().collect(Collectors.toList());
//    	System.out.println(list);
    	Object[] str = (Object[]) list.stream().filter(x -> !x.equals("1")).distinct().toArray();
    	for (Object o : str) {
			System.out.println(o);
		}
	}
      
}
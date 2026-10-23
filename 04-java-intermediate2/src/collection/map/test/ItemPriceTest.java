package collection.map.test;

import java.util.*;

public class ItemPriceTest {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("사과", 500);
		map.put("바나나", 500);
		map.put("망고", 1000);
		map.put("딸기", 1000);

		//LinkedList<String> list = new LinkedList<>();
		List<String> list = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : map.entrySet()){
			if (entry.getValue().equals(1000)) {
				list.add(entry.getKey());
			}
		//for (String entry : map.keySet()) {
			/*
			if (map.get(key) == 1000) {
				list.add(key);
			}
			*/

		}
		System.out.println(list);
	}
}

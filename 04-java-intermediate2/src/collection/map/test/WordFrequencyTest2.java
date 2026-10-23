package collection.map.test;

import java.util.HashMap;

public class WordFrequencyTest2 {

	public static void main(String[] args) {
		String text = "orange banana apple apple banana apple";

		HashMap<String, Integer> map = new HashMap<>();
		String[] words = text.split(" ");
		for (String word : words) {
			Integer count = map.getOrDefault(word, 0) + 1;
			map.put(word, count);
		}

		System.out.println(map);
	}

}

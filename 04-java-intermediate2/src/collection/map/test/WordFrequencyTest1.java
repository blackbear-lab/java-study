package collection.map.test;

import java.util.HashMap;

public class WordFrequencyTest1 {

	public static void main(String[] args) {
		String text = "orange banana apple apple banana apple";

		HashMap<String, Integer> map = new HashMap<>();
		String[] words = text.split(" ");

		for (String word : words) {
			Integer count = map.get(word);
			if (count == null) {
				count = 0;
			}
			count++;
			map.put(word, count);
		}
		System.out.println(map);
	}

}

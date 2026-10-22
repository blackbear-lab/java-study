package collection.set.test;

import java.util.*;

public class UniqueNamesTest2 {

	public static void main(String[] args) {
		Integer[] inputArr = {30, 20, 20, 10, 10};

		Set<Integer> set = new LinkedHashSet<>(List.of(inputArr));

		for (Integer s : set) {
			System.out.println(s);
		}

	}
}

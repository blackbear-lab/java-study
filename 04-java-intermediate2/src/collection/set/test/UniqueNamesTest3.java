package collection.set.test;


import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UniqueNamesTest3 {

	public static void main(String[] args) {
		Integer[] inputArr = {30, 20, 20, 10, 10};

		Set<Integer> tree = new TreeSet<>(List.of(inputArr));

		for (Integer s : tree) {
			System.out.println(s);
		}
	}
}

package collection.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmptyListMain {
	public static void main(String[] args) {
		//空可変リスト生成
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();

		//空不変リスト生成
		List<Integer> list3 = Collections.emptyList();
		List<Integer> list4 = List.of();

		System.out.println("list3 = " + list3.getClass());
		System.out.println("list4 = " + list4.getClass());

		List<Integer> list5 = Arrays.asList(1,2,3);
		List<Integer> list6 = List.of(1,2,3);

		Integer[] arr = {1, 2, 3, 4, 5};
		List<Integer> arrList = Arrays.asList(arr);
		arrList.set(0, 100);
		System.out.println("arr = " + Arrays.toString(arr));
		System.out.println("arrList = " + arrList);

		List<Integer> arr1 = List.of(arr);
	}
}

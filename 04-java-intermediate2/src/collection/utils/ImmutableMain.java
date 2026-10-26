package collection.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableMain {
	public static void main(String[] args) {
		//不変list生成
		List<Integer> list = List.of(1, 2, 3);

		//可変list
		ArrayList<Integer> mutableList = new ArrayList<>(list);
		mutableList.add(4);
		System.out.println("mutableList = " + mutableList);
		System.out.println("mutableList.getClass() = " + mutableList.getClass());

		//不変list
		List<Integer> unmodifiableList = Collections.unmodifiableList(mutableList);
		System.out.println("unmodifiableList.getClass() = " + unmodifiableList.getClass());
		System.out.println("unmodifiableList = " + unmodifiableList);
	}
}

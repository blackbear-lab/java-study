package collection.set;

import java.util.Arrays;
import java.util.LinkedList;

public class HashStart5 {

	static final int CAPACITY = 10;

	public static void main(String[] args) {
		// 入力データ: [1, 2, 5, 8, 14, 99]

		LinkedList<Integer>[] buckets = new LinkedList[CAPACITY];
		System.out.println("buckets = " + Arrays.toString(buckets));

		for (int i = 0; i < CAPACITY; i++) {
			buckets[i] = new LinkedList<>();
		}

		System.out.println("buckets = " + Arrays.toString(buckets));
		add(buckets, 1);
		add(buckets, 2);
		add(buckets, 5);
		add(buckets, 8);
		add(buckets, 14);
		add(buckets, 99);
		add(buckets, 9);	//重複
		System.out.println("buckets = " + Arrays.toString(buckets));

		//検索
		int searchValue = 9;
		boolean contains = contains(buckets, searchValue);
		System.out.println("buckets.contains("+searchValue+") = " + contains);
	}

	private static void add(LinkedList<Integer>[] buckets, int value) {
		int hashIndex = hashIndex(value);
		LinkedList<Integer> bucket = buckets[hashIndex];
		if (!bucket.contains(value)) {
			bucket.add(value);
		}
	}

	private static boolean contains(LinkedList<Integer>[] buckets, int searchValue) {
		int hashIndex = hashIndex(searchValue);
		LinkedList<Integer> bucket = buckets[hashIndex];
		return bucket.contains(searchValue);
	}

	static int hashIndex(int value) {
		return value % CAPACITY;
	}

}

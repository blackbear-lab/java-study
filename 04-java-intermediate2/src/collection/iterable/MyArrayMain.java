package collection.iterable;

import java.util.Iterator;

public class MyArrayMain {

	public static void main(String[] args) {
		MyArray myArray = new MyArray(new int[]{1, 2, 3, 4});
		Iterator<Integer> iterator = myArray.iterator();
		System.out.println("iterator使用");

		while (iterator.hasNext()) {
			Integer value = iterator.next();
			System.out.println("value = " + value);
		}

		//追加
		System.out.println("for-each使用");
		for (int value : myArray) {
			System.out.println("value = " + value);
		}
	}
}

package collection.array;

import java.util.Arrays;

public class ArrayMain1 {

	public static void main(String[] args) {
		int[] arr = new int[5];
		//index入力： 0(1)
		System.out.println("==index入力： 0(1) ==");
		arr[0] = 1;
		arr[1] = 2;
		arr[2] = 3;
		System.out.println(arr);
		System.out.println(Arrays.toString(arr));

		System.out.println("== index変更： 0(1) ==");
		arr[2] = 10;
		System.out.println(Arrays.toString(arr));

		System.out.println("==index照会： 0(1)==");
		System.out.println("arr[2] = " + arr[2]);

		System.out.println("==配列検索： 0(n)==");
		System.out.println(Arrays.toString(arr));
		int value = 10;
		for (int i = 0; i < arr.length; i++) {
			System.out.println("arr[" + i + "]: " + arr[i]);
			if (arr[i] == value) {
				System.out.println(value + " 発見");
			}
		}
	}
}

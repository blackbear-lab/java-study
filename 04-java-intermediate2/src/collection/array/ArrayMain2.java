package collection.array;

import java.util.Arrays;

public class ArrayMain2 {

	public static void main(String[] args) {
		int[] arr = new int[5];
		//index入力： 0(1)
		System.out.println("==index入力： 0(1) ==");
		arr[0] = 1;
		arr[1] = 2;
		System.out.println(Arrays.toString(arr));

		//配列の1番目に位置に追加
		//基本配列のデータを一個ずつ後ろに移動させ、配列の一番目に追加
		System.out.println("配列の1番目に３を追加 0(n)");
		int newValue = 3;
		addFirst(arr, newValue);
		System.out.println(Arrays.toString(arr));

		//index位置に追加
		//基本配列のデータを一個ずつ後ろに移動させ、配列のindexに追加
		System.out.println("配列のindex(2)位置に4を追加 0(n)");
		int index = 2;
		int value = 4;
		addAtIndex(arr, index, value);
		System.out.println(Arrays.toString(arr));

		System.out.println("配列の最後に5を追加 0(1)");
		addLast(arr, 5);
		System.out.println(Arrays.toString(arr));

	}

	private static void addLast(int[] arr, int i) {
		arr[arr.length - 1] = 5;
	}

	private static void addAtIndex(int[] arr, int index, int value) {
		for (int i = arr.length - 1; i > index; i--) {
			arr[i] = arr[i-1];
		}
		arr[index] = value;
	}

	private static void addFirst(int[] arr, int newValue) {
		for (int i = arr.length - 1; i > 0; i--) {
			arr[i] = arr[i - 1];
		}
		arr[0] = newValue;
	}
}

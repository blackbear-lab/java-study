package collection.array;

import java.util.ArrayList;

public class MyArrayListV2Main {
	public static void main(String[] args) {
		MyArrayListV2 list = new MyArrayListV2(2);
		System.out.println("==データ追加==");
		System.out.println(list);
		list.add("a");
		System.out.println(list);
		list.add("b");
		System.out.println(list);
		list.add("c");
		System.out.println(list);
		list.add("d");
		System.out.println(list);
		list.add("e");
		System.out.println(list);

		// 範囲超過, capacityが増えない場合エラー発生
		list.add("f");
		System.out.println(list);


	}
}

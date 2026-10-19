package collection.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JavaListPerformanceTest {

	public static void main(String[] args) {
		int size = 50_000;
		System.out.println("==ArrayList 追加==");
		addFirst(new ArrayList<>(), size);
		addMid(new ArrayList<>(), size);	//探すまで 0(1), データ追加（押し）0(n)
		ArrayList<Integer> arrayList = new ArrayList<>();
		addLast(arrayList, size);	//探すまで 0(1), データ追加（押し）0(n)

		int loop = 10000;
		System.out.println("==ArrayList 照会==");
		getIndex(arrayList, loop, 0);
		getIndex(arrayList, loop, size/ 2);
		getIndex(arrayList, loop, size - 1);
		System.out.println("==ArrayList 検索==");
		search(arrayList, loop, 0);
		search(arrayList, loop, size / 2);
		search(arrayList, loop, size - 1);


		System.out.println("==LinkedList 追加==");
		addFirst(new LinkedList<>(), size);
		addMid(new LinkedList<>(), size);	//探すまで 0(n), データ追加(1)
		LinkedList<Integer> linkedList = new LinkedList<>();
		addLast(linkedList, size);	//探すまで 0(n), データ追加(1)
		System.out.println("==LinkedList 照会==");
		getIndex(linkedList, loop, 0);
		getIndex(linkedList, loop, size/ 2);
		getIndex(linkedList, loop, size - 1);
		System.out.println("==LinkedList 検索==");
		search(linkedList, loop, 0);
		search(linkedList, loop, size / 2);
		search(linkedList, loop, size - 1);
	}

	private static void addFirst(List<Integer> list, int size) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			list.add(0, i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("前に追加 - サイズ：" + size + ", 計算時間" + (endTime - startTime) + "ms");
	}

	private static void addMid(List<Integer> list, int size) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			list.add(i / 2, i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("真中に追加 - サイズ：" + size + ", 計算時間" + (endTime - startTime) + "ms");
	}

	private static void addLast(List<Integer> list, int size) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("後に追加 - サイズ：" + size + ", 計算時間" + (endTime - startTime) + "ms");
	}

	private static void getIndex(List<Integer> list, int loop, int index) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < loop; i++) {
			list.get(index);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("index：" + index + ", 繰り返し： "+ loop +", 計算時間"+ (endTime - startTime) + "ms");
	}

	private static void search(List<Integer> list, int loop, int findValue) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < loop; i++) {
			list.indexOf(findValue);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("findValue：" + findValue + ", 繰り返し： "+ loop +", 計算時間"+ (endTime - startTime) + "ms");
	}
}

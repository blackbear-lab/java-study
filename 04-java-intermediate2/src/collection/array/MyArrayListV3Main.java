package collection.array;

public class MyArrayListV3Main {
	public static void main(String[] args) {
		MyArrayListV3 list = new MyArrayListV3();
		//最後に追加　//0(1)
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println(list);

		//ほしい位置に追加
		System.out.println("addLast");
		list.add(3, "addList");	//0(1)
		System.out.println(list);

		System.out.println("addFirst");
		list.add(0, "addFirst");	//0(n)
		System.out.println(list);

		//削除
		Object removed1 = list.remove(4);
		System.out.println("removed(4) = " + removed1);
		System.out.println(list);

		Object removed2 = list.remove(0);
		System.out.println("removed2(0) = " + removed2);
		System.out.println(list);
	}
}

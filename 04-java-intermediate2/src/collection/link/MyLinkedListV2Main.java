package collection.link;

public class MyLinkedListV2Main {
	public static void main(String[] args) {
		MyLinkedListV2 list = new MyLinkedListV2();
		// 最後に追加 // 0(n)
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println(list);

		// 1番目に追加
		System.out.println("一番目の項目に追加");
		list.add(0, "d");    //0(1)
		System.out.println(list);

		System.out.println("一番目の項目から削除");
		list.remove(0);
		System.out.println(list);

		// ちゅうかんこうもくについか、削除
		System.out.println("中間項目の追加");
		list.add(1, "e");    //0(1)
		System.out.println(list);

		System.out.println("中間項目から削除");
		list.remove(1);	//remove 0(1)
		System.out.println(list);
	}
}

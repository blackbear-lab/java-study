package collection.link;



public class MyLinkedListV1Main {
	public static void main(String[] args) {
		MyLinkedListV1 list = new MyLinkedListV1();
		System.out.println("==データ追加==");
		System.out.println(list);
		list.add("a");
		System.out.println(list);
		list.add("b");
		System.out.println(list);
		list.add("c");
		System.out.println(list);


		System.out.println("==機能使用==");
		System.out.println("list.size() = " + list.size());
		System.out.println("list.get(1) = " + list.get(1));
		System.out.println("list.indexOf('c') = " + list.indexOf("c"));
		System.out.println("list.set(2,'z') = " + list.set(2, "z"));
		System.out.println(list);

		System.out.println("==範囲超過==");
		list.add("d");
		System.out.println(list);
		list.add("e");
		System.out.println(list);

		// 範囲超過, capacityが増えない場合エラー発生
		list.add("f");
		System.out.println(list);
	}
}

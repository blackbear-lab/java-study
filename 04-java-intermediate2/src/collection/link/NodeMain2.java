package collection.link;

public class NodeMain2 {
	public static void main(String[] args) {
		//ノード生成、連結：Ａ-> B -> C
		Node first = new Node("A");
		first.next = new Node("B");
		first.next.next = new Node("C");

		System.out.println("つながったノード出力");
		System.out.println(first);

		Node second = first.next;
		System.out.println(second);
	}
}

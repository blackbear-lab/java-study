package collection.link;

public class NodeMain1 {
	public static void main(String[] args) {
		//ノード生成、連結：Ａ-> B -> C
		Node first = new Node("A");
		first.next = new Node("B");
		first.next.next = new Node("C");

		System.out.println("すべてのノード探索");
		Node x = first;
		while (x != null) {
			System.out.println("x.item = " + x.item);
			x = x.next;
		}

	}
}

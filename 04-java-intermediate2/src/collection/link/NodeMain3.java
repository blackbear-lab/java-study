package collection.link;

import java.util.EventListener;

import static java.awt.AWTEventMulticaster.add;

public class NodeMain3 {

	public static void main(String[] args) {
		//ノード生成、連結：Ａ-> B -> C
		Node first = new Node("A");
		first.next = new Node("B");
		first.next.next = new Node("C");

		System.out.println(first);
		
		//すべてのノード探索
		System.out.println("すべてのノード探索");
		printAll(first);

		//最後のノード照会
		Node lastNode = getLastNode(first);
		System.out.println("lastNode = " + lastNode);

		//特定indexのノード照会
		int index = 2;
		Node index2Node = getNode(first, index);
		System.out.println("index2Node = " + index2Node.item);

		//データ追加
		System.out.println("データ追加");
		add(first, "D");
		System.out.println(first);
		add(first, "E");
		System.out.println(first);
		add(first, "F");
		System.out.println(first);

	}

	private static Node getNode(Node node, int index) {
		Node x = node;
		for (int i = 0; i < index; i++) {
			x = x.next;
		}
		return x;
	}

	private static void printAll(Node node) {
		Node x = node;
		while (x != null) {
			System.out.println(x.item);
			x = x.next;
		}
	}

	private static void add(Node node, String param) {
		Node lastNode = getLastNode(node);
		lastNode.next = new Node(param);
	}

	private static Node getLastNode(Node node) {
		Node x = node;
		while (x.next != null) {
			x = x.next;
		}
		return x;
	}
}

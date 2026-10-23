package collection.deque;

import java.util.ArrayDeque;

public class DequeStackMain {

	public static void main(String[] args) {
		ArrayDeque<Integer> deque = new ArrayDeque<>();

		// データ追加
		deque.push(1);
		deque.push(2);
		deque.push(3);
		System.out.println(deque);

		//次取り出す要素確認(取り出さず、確認のみ)
		System.out.println("deque.peek() = " + deque.peek());

		// データ取り出し
		System.out.println("deque.pop() = " + deque.pop());
		System.out.println("deque.pop() = " + deque.pop());
		System.out.println("deque.pop() = " + deque.pop());
		System.out.println(deque);
	}
}

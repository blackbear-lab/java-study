package collection.deque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DequeMain {
	public static void main(String[] args) {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		//Deque<Integer> deque = new LinkedList<>();

		//データ追加
		deque.offerFirst(1);
		System.out.println(deque);
		deque.offerFirst(2);
		System.out.println(deque);
		deque.offerLast(3);
		System.out.println(deque);
		deque.offerLast(4);
		System.out.println(deque);

		//次取り出す要素確認(取り出さず、確認のみ)
		System.out.println("deque.peekFirst() = " + deque.peekFirst());
		System.out.println("deque.peekLast() = " + deque.peekLast());

		// データ取り出し
		System.out.println("deque.pollFirst() = " + deque.pollFirst());
		System.out.println("deque = " + deque);
		System.out.println("deque.pollFirst() = " + deque.pollFirst());
		System.out.println("deque.pollLast() = " + deque.pollLast());
		System.out.println("deque.pollLast() = " + deque.pollLast());
	}
}

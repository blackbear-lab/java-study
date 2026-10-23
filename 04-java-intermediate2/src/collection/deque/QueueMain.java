package collection.deque;

import java.util.ArrayDeque;

public class QueueMain {
	public static void main(String[] args) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		//Queue<Integer> queue = new LinkedList<>();


		//データ追加
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		System.out.println(queue);

		//次取り出す要素確認(取り出さず、確認のみ)
		System.out.println("queue.peek() = " + queue.peek());

		// データ取り出し
		System.out.println("queue.poll() = " + queue.poll());
		System.out.println(queue);
		System.out.println("queue.poll() = " + queue.poll());
		System.out.println("queue.poll() = " + queue.poll());
		System.out.println(queue);
	}
}

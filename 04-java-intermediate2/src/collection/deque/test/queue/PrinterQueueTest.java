package collection.deque.test.queue;

import java.util.ArrayDeque;

public class PrinterQueueTest {
	public static void main(String[] args) {
		ArrayDeque<String> printQueue = new ArrayDeque<>();

		printQueue.offer("doc1");
		printQueue.offer("doc2");
		printQueue.offer("doc3");

		System.out.println("出力： " + printQueue.poll());
		System.out.println("出力： " + printQueue.poll());
		System.out.println("出力： " + printQueue.poll());
	}
}

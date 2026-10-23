package collection.deque.test.stack;

import java.util.ArrayDeque;

public class SimpleHistoryTest {
	public static void main(String[] args) {
		ArrayDeque<String> stack = new ArrayDeque<>();
		stack.push("youtube.com");
		stack.push("google.com");
		stack.push("facebook.com");

		System.out.println(stack.poll());
		System.out.println(stack.poll());
		System.out.println(stack.poll());
	}

}

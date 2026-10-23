package collection.deque.test.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserHistory {

	private Deque<String> history = new ArrayDeque<>();
	private String currentPage = null;


	public void visitPage(String url) {
		if (currentPage != null) {
			history.push(currentPage);
		}
		currentPage = url;
		System.out.println("接続： " + url);
	}

	public String goBack() {
		if (!history.isEmpty()) {
			currentPage = history.pop();
			System.out.println("以前のページに移動： " + currentPage);
			return currentPage;
		}
		return null;
	}
}

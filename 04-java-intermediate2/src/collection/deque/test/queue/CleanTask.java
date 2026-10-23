package collection.deque.test.queue;

public class CleanTask implements Task {

	@Override
	public void execute() {
		System.out.println("使用しない資源整理．．．");
	}
}

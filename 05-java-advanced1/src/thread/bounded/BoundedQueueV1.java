package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue {

	private final Queue<String> queue = new ArrayDeque<>();
	private final int max;

	public BoundedQueueV1(int max) {
		this.max = max;
	}

	@Override
	public synchronized void put(String data) {
		if (queue.size() == max) {
			log("[put] キューが満杯になる。捨てる： " + data);
			return;
		}

		queue.offer(data);
	}

	@Override
	public synchronized String take() {
		if (queue.isEmpty()) {
			return null;
		}
		return queue.poll();
	}

	@Override
	public String toString() {
		return queue.toString();
	}
}

package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {

	private final Queue<String> queue = new ArrayDeque<>();
	private final int max;

	public BoundedQueueV2(int max) {
		this.max = max;
	}

	@Override
	public synchronized void put(String data) {
		while (queue.size() == max) {
			log("[put] キューが満杯、生産者待機");
			sleep(1000);
		}
		queue.offer(data);
	}

	@Override
	public synchronized String take() {
		while (queue.isEmpty()) {
			log("[take] キューにデータ存在しない、消費者待機");
			sleep(1000);
		}
		return queue.poll();
	}

	@Override
	public String toString() {
		return queue.toString();
	}
}

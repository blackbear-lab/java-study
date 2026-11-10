package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV3 implements BoundedQueue {

	private final Queue<String> queue = new ArrayDeque<>();
	private final int max;

	public BoundedQueueV3(int max) {
		this.max = max;
	}

	@Override
	public synchronized void put(String data) {
		while (queue.size() == max) {
			log("[put] キューが満杯、生産者待機");
			try {
				wait();
				log("[put] 生産者起床");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		queue.offer(data);
		log("[put] 生産者データ保存、notify() 呼出");
		notify();
	}

	@Override
	public synchronized String take() {
		while (queue.isEmpty()) {
			log("[take] キューにデータ存在しない、消費者待機");
			try {
				wait();	//大気中にロックを返却する
				log("[take] 消費者起床");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		String data = queue.poll();
		log("[put] 消費者データ獲得、notify() 呼出");
		notify();
		return data;
	}

	@Override
	public String toString() {
		return queue.toString();
	}
}

package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV4 implements BoundedQueue {

	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	private final Queue<String> queue = new ArrayDeque<>();
	private final int max;

	public BoundedQueueV4(int max) {
		this.max = max;
	}

	@Override
	public void put(String data) {
		lock.lock();
		try {
			while (queue.size() == max) {
				log("[put] キューが満杯、生産者待機");
				try {
					condition.await();
					log("[put] 生産者起床");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			queue.offer(data);
			log("[put] 生産者データ保存、condition.signal() 呼出");
			condition.signal();
		}finally {
			lock.unlock();
		}


	}

	@Override
	public String take() {
		lock.lock();
		try {
			while (queue.isEmpty()) {
				log("[take] キューにデータ存在しない、消費者待機");
				try {
					condition.await();	//待機中にロックを返却する
					log("[take] 消費者起床");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			String data = queue.poll();
			log("[put] 消費者データ獲得、condition.signal() 呼出");
			condition.signal();
			return data;
		}finally {
			lock.unlock();
		}

	}

	@Override
	public String toString() {
		return queue.toString();
	}
}

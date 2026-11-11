package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue {

	private final Lock lock = new ReentrantLock();
	private final Condition producerCond = lock.newCondition();
	private final Condition consumerCond = lock.newCondition();

	private final Queue<String> queue = new ArrayDeque<>();
	private final int max;

	public BoundedQueueV5(int max) {
		this.max = max;
	}

	@Override
	public void put(String data) {
		lock.lock();
		try {
			while (queue.size() == max) {
				log("[put] キューが満杯、生産者待機");
				try {
					producerCond.await();
					log("[put] 生産者起床");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			queue.offer(data);
			log("[put] 生産者データ保存、consumerCond.signal() 呼出");
			consumerCond.signal();
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
					consumerCond.await();	//待機中にロックを返却する
					log("[take] 消費者起床");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			String data = queue.poll();
			log("[put] 消費者データ獲得、producerCond.signal() 呼出");
			producerCond.signal();
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

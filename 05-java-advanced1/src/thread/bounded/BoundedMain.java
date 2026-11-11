package thread.bounded;

import java.util.ArrayList;
import java.util.List;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedMain {

	public static void main(String[] args) {
		// 1. BoundedQueue　選択
		//BoundedQueue queue = new BoundedQueueV1(2);
		//BoundedQueue queue = new BoundedQueueV2(2);
		//BoundedQueue queue = new BoundedQueueV3(2);
		//BoundedQueue queue = new BoundedQueueV4(2);
		//BoundedQueue queue = new BoundedQueueV5(2);
		//BoundedQueue queue = new BoundedQueueV6_1(2);
		//BoundedQueue queue = new BoundedQueueV6_2(2);
		//BoundedQueue queue = new BoundedQueueV6_3(2);
		BoundedQueue queue = new BoundedQueueV6_4(2);

		// 2. 生産者、消費者実行順序選択、必ず一つの未選択
		produceFirst(queue);	//生産者優先実行
		//consumerFirst(queue);	//消費者優先実行
	}

	private static void consumerFirst(BoundedQueue queue) {
		log("== [消費者優先実行] 開始、" + queue.getClass().getSimpleName() + " ==");
		List<Thread> threads = new ArrayList<>();
		startConsumer(queue, threads);
		printAllState(queue, threads);
		startProducer(queue, threads);
		printAllState(queue, threads);
		log("== [消費者優先実行] 終了、" + queue.getClass().getSimpleName() + " ==");
	}

	private static void produceFirst(BoundedQueue queue) {
		log("== [生産者優先実行] 開始、" + queue.getClass().getSimpleName() + " ==");
		List<Thread> threads = new ArrayList<>();
		startProducer(queue, threads);
		printAllState(queue, threads);
		startConsumer(queue, threads);
		printAllState(queue, threads);
		log("== [生産者優先実行] 終了、" + queue.getClass().getSimpleName() + " ==");
	}

	private static void startProducer(BoundedQueue queue, List<Thread> threads) {
		System.out.println();
		log("生産者 開始");
		for (int i = 1; i <= 3; i++) {
			Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
			threads.add(producer);
			producer.start();
			sleep(100);
		}
	}

	private static void printAllState(BoundedQueue queue, List<Thread> threads) {
		System.out.println();
		log("現在状態出力、キューでーた： " + queue);
		for (Thread thread : threads) {
			log(thread.getName()+ ": " + thread.getState());
		}
	}

	private static void startConsumer(BoundedQueue queue, List<Thread> threads) {
		System.out.println();
		log("消費者開始");
		for (int i = 1; i <= 3; i++) {
			Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
			threads.add(consumer);
			consumer.start();
			sleep(100);

		}
	}
}

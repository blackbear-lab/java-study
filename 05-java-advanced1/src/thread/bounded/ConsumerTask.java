package thread.bounded;

import static util.MyLogger.log;

public class ConsumerTask implements Runnable {

	private BoundedQueue queue;

	public ConsumerTask(BoundedQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		log("[消費試行]     ? <- " + queue);
		String data = queue.take();
		log("[消費完了] " + data + " <- " + queue);
	}
}

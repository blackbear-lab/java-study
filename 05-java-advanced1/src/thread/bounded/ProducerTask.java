package thread.bounded;

import static util.MyLogger.log;

public class ProducerTask implements Runnable {

	private BoundedQueue queue;
	private String request;

	public ProducerTask(BoundedQueue queue, String request) {
		this.queue = queue;
		this.request = request;
	}

	@Override

	public void run() {
		log("[生産試行] " + request + " -> " + queue);
		queue.put(request);
		log("[生産完了] " + request + " -> " + queue);
	}
}

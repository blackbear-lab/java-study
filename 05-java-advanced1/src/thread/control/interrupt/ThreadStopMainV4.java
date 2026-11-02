package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

	public static void main(String[] args) {

		MyTask task = new MyTask();
		Thread thread = new Thread(task, "work");
		thread.start();

		sleep(100);
		log("作業中止指示 thread.interrupt()");
		thread.interrupt();
		log("workスレッド`割り込み状態1 = " + thread.isInterrupted() );
	}

	static class MyTask implements Runnable {

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				log("作業中");
			}
			log("workスレッド割り込み状態2 = " + Thread.currentThread().isInterrupted());

			try {
				log("資源整理");
				Thread.sleep(1000);
				log("資源終了");
			} catch (InterruptedException e) {
				log("資源整理失敗 - 資源整理中割り込み発生");
				log("workスレッド割り込み状態3 = " + Thread.currentThread().isInterrupted());
			}

		}
	}

}

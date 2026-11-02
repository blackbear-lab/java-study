package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

	public static void main(String[] args) {

		MyTask task = new MyTask();
		Thread thread = new Thread(task, "work");
		thread.start();

		sleep(4000);
		log("作業中止指示 thread.interrupt()");
		thread.interrupt();
		log("workスレッド`割り込み状態1 = " + thread.isInterrupted() );
	}

	static class MyTask implements Runnable {

		@Override
		public void run() {
			try {
				while (true) {
					log("作業中");
					Thread.sleep(3000);
				}
			} catch (InterruptedException e) {
				log("workスレッド割り込み状態2 = " + Thread.currentThread().isInterrupted());
				log("interrupt message= " + e.getMessage());
				log("state = " + Thread.currentThread().getState());
			}
			log("資源整理");
			log("資源終了");
		}
	}

}

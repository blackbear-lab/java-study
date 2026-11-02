package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {

	public static void main(String[] args) {

		MyTask task = new MyTask();
		Thread thread = new Thread(task, "work");
		thread.start();

		sleep(4000);
		log("作業中止指示 runFlag=false");
		task.runFlag = false;
	}

	static class MyTask implements Runnable {

		volatile boolean runFlag = true;

		@Override
		public void run() {
			while (runFlag) {
				log("作業中");
				sleep(3000);
			}
			log("資源整理");
			log("資源終了");
		}
	}

}

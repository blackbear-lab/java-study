package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {
	public static void main(String[] args) {

		MyTask task = new MyTask();
		Thread t = new Thread(task, "work");
		log("runFlag = " + task.runFlag);
		t.start();

		sleep(1000);
		log("runFlagをfalseに変更");
		task.runFlag = false;
		log("runFlag = " + task.runFlag);
		log("main終了");
	}

	static class MyTask implements Runnable {

		boolean runFlag = true;
		//volatile boolean runFlag = true;

		@Override
		public void run() {
			log("task開始");
			while (runFlag) {
				//runFlagがfalseになると脱出
			}
			log("task終了");
		}
	}

}

package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {
	public static void main(String[] args) {
		Thread thread1 = new Thread(new ParkTest(), "Thread-1");
		thread1.start();

		// 少し待機して、Thread-1がpark状態になるようにする。
		sleep(100);
		log("Thread-1 state: " + thread1.getState());

		log("main -> unpark(Thread-1)");
		LockSupport.unpark(thread1);	// 1. unpark
		//thread1.interrupt();	// 2.　interrupt()
	}

	static class ParkTest implements Runnable {
		@Override
		public void run() {
			log("park 開始");
			LockSupport.park();
			log("park 終了, state: " + Thread.currentThread().getState());
			log("割り込み状態: " + Thread.currentThread().isInterrupted());
		}
	}

}

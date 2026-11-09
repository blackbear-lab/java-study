package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV2 {
	public static void main(String[] args) {
		Thread thread1 = new Thread(new ParkTest(), "Thread-1");
		thread1.start();

		// 少し待機して、Thread-1がpark状態になるようにする。
		sleep(100);
		log("Thread-1 state: " + thread1.getState());
	}

	static class ParkTest implements Runnable {
		@Override
		public void run() {
			log("park 開始");
			LockSupport.parkNanos(2000_000000);
			log("park 終了, state: " + Thread.currentThread().getState());
			log("割り込み状態: " + Thread.currentThread().isInterrupted());
		}
	}

}

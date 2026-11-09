package thread.sync.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx {
	private final Lock nonFairLock = new ReentrantLock();

	private final Lock fairLock = new ReentrantLock(true);

	public void nonFairLockTest() {
		nonFairLock.lock();
		try {

		}finally {
			nonFairLock.unlock();
		}
	}

	public void fairLockTest() {
		fairLock.lock();
		try {

		}finally {
			fairLock.unlock();
		}
	}
}

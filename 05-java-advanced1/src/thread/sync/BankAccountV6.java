package thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount {

	private int balance;

	private final Lock lock = new ReentrantLock();

	public BankAccountV6(int initialBalance) {
		this.balance = initialBalance;
	}

	@Override
	public boolean withdraw(int amount) {
		log("取引開始: " + getClass().getSimpleName());
		//残高が出金額より少ない場合、処理可能

		try {
			if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
				log("[進入失敗] 先に処理中の作業が存在します。");
				return false;
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		try {
			//臨界領域
			log("[検証開始] 出金額：" + amount + ", 残高： " + balance);

			if (balance < amount) {
				log("[検証失敗] 出金額：" + amount + ", 残高： " + balance);
				return false;
			}
			//残高が出金額より多い場合、処理
			log("[検証完了] 出金額：" + amount + ", 残高： " + balance);
			sleep(1000);
			balance = balance - amount;
			log("[引き出し完了] 出金額：" + amount + ", 残高： " + balance);
			//臨界領域
		}finally {
		}

		log("取引終了");
		return true;
	}

	@Override
	public synchronized int getBalance() {
		lock.lock();	// ReentrantLockを使用してlockをかける
		try {
			return balance;
		}finally {
			lock.unlock();	// ReentrantLockを使用してlock解除
		}
	}
}

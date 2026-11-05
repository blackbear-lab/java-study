package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV1 implements BankAccount {

	private int balance;

	public BankAccountV1(int initialBalance) {
		this.balance = initialBalance;
	}

	@Override
	public boolean withdraw(int amount) {
		log("取引開始: " + getClass().getSimpleName());
		//残高が出金額より少ない場合、処理可能

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

		log("取引終了");
		return true;
	}

	@Override
	public int getBalance() {
		return balance;
	}
}

package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockBad {
    private volatile boolean lock = false;

    public void lock() {
        log("ロック獲得試行");
        while (true) {
            if (!lock) {    //1. ロック使用有無確認
                sleep(100); // 問題状況確認用、スレッド待機
                lock = true;    // 2. ロックの値変更
                break;
            } else {
                //ロック獲得までスピン待機（ビジーウェイト）する。
                log("ロック獲得失敗 - スピン待機");
            }
        }
        log("ロック獲得完了");
    }

    public void unlock() {
        lock = false;
        log("ロック返却完了");
    }
}

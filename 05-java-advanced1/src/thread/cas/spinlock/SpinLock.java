package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLock {
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("ロック獲得試行");
        while (!lock.compareAndSet(false, true)) {
            //ロック獲得までスピン待機（ビジーウェイト）する。
            log("ロック獲得失敗 - スピン待機");
        }
        log("ロック獲得完了");
    }

    public void unlock() {
        lock.set(false);
        log("ロック返却完了");
    }
}

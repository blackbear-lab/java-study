package util;

import static util.MyLogger.log;

public class ThreadUtils {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log("割り込み発生, " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

package thread.control.join;

import util.ThreadUtils;

import static util.MyLogger.log;
import static util.ThreadUtils.*;

public class JoinMainV0 {
    public static void main(String[] args) {
        log("Start");
        Thread thread1 = new Thread(new Job(), "thread-1");
        Thread thread2 = new Thread(new Job(), "thread-2");

        thread1.start();
        thread2.start();
        log("End");

    }

    static class Job implements Runnable {
        @Override
        public void run() {
            log("作業開始");
            sleep(2000);
            log("作業完了");
        }
    }

}

package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

import static util.MyLogger.log;

public class MyPrinterV3 {

	public static void main(String[] args) {

		Printer printer = new Printer();
		Thread printerThread = new Thread(printer, "printer");
		printerThread.start();

		Scanner userInput = new Scanner(System.in);
		while (true) {
			log("プリントする文書を入力してください。終了（ｑ）： ");
			String input = userInput.nextLine();
			if (input.equals("q")) {
				printerThread.interrupt();
				break;
			}
			printer.addJob(input);

		}
	}

	static class Printer implements Runnable {
		Queue<String> jobQueue = new ConcurrentLinkedDeque<>();

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				if (jobQueue.isEmpty()) {
					continue;
				}

				try {
					String job = jobQueue.poll();
					log("出力開始： " + job + ",大気文書： " + jobQueue);
					Thread.sleep(3000);
					log("出力完了");
				} catch (InterruptedException e) {
					log("`割り込み発生！");
					break;
				}
			}
			log("プリンター終了");
		}


		public void addJob(String input) {
			jobQueue.offer(input);
		}
	}

}

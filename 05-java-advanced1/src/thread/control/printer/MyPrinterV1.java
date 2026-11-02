package thread.control.printer;

import nested.local.Printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV1 {

	public static void main(String[] args) {

		Printer printer = new Printer();
		Thread printerThread = new Thread(printer, "printer");
		printerThread.start();

		Scanner userInput = new Scanner(System.in);
		while (true) {
			log("プリントする文書を入力してください。終了（ｑ）： ");
			String input = userInput.nextLine();
			if (input.equals("q")) {
				printer.work = false;
				break;
			}
			printer.addJob(input);

		}
	}

	static class Printer implements Runnable {
		volatile boolean work = true;
		Queue<String> jobQueue = new ConcurrentLinkedDeque<>();

		@Override
		public void run() {
			while (work) {
				if (jobQueue.isEmpty()) {
					continue;
				}
				String job = jobQueue.poll();
				log("出力開始： " + job + ",大気文書： " + jobQueue);
				sleep(3000);
				log("出力完了");
			}
			log("プリンター終了");
		}


		public void addJob(String input) {
			jobQueue.offer(input);
		}
	}

}

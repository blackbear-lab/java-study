package nested.anonymous.ex;

import java.util.Random;

public class Ex1RefMain1 {

	public static void hello(Process process) {
		System.out.println("プログラム開始");
		process.run();
		System.out.println("プログラム終了");
	}

	static class Dice implements Process {
		@Override
		public void run() {
			int randomValue = new Random().nextInt(6) + 1;
			System.out.println("サイコロ = " + randomValue);
		}
	}

	static class Sum implements Process {
		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				System.out.println("i = " + i);
			}
		}
	}

	public static void main(String[] args) {
		Dice dice = new Dice();
		Sum sum = new Sum();
		System.out.println("hello実装");
		hello(dice);
		hello(sum);
	}
}

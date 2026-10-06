package nested.anonymous.ex;

import java.util.Random;

public class Ex1RefMain3 {

	public static void hello(Process process) {
		System.out.println("プログラム開始");
		process.run();
		System.out.println("プログラム終了");
	}

	public static void main(String[] args) {
		Process dice = new Process() {
			@Override
			public void run() {
				int randomValue = new Random().nextInt(6) + 1;
				System.out.println("サイコロ = " + randomValue);
			}
		};

		Process sum = new Process() {
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					System.out.println("i = " + i);
				}
			}
		};

		System.out.println("hello実装");
		hello(dice);
		hello(sum);
	}
}

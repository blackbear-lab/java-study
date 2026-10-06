package nested.anonymous.ex;

import java.util.Random;

public class Ex1RefMain5 {

	public static void hello(Process process) {
		System.out.println("プログラム開始");
		process.run();
		System.out.println("プログラム終了");
	}

	public static void main(String[] args) {
		System.out.println("hello実行");
		hello(() -> {
			int randomValue = new Random().nextInt(6) + 1;
			System.out.println("サイコロ = " + randomValue);
		});

		hello(() -> {
			for (int i = 0; i < 3; i++) {
				System.out.println("i = " + i);
			}
		});
	}
}

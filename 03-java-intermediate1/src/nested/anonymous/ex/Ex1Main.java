package nested.anonymous.ex;

import java.util.Random;

public class Ex1Main {

	public static void helloDice() {
		System.out.println("プログラム開始");

		//コードスニペット開始
		int randomValue = new Random().nextInt(6) + 1;
		System.out.println("サイコロ = " + randomValue);
		//コードスニペット終了

		System.out.println("プログラム終了");
	}


	public static void helloSum() {
		System.out.println("プログラム開始");

		//コードスニペット開始
		for (int i = 0; i < 3; i++) {
			System.out.println("i = " + i);
		}
		//コードスニペット終了

		System.out.println("プログラム終了");
	}

	public static void main(String[] args) {
		helloDice();
		helloSum();
	}
}

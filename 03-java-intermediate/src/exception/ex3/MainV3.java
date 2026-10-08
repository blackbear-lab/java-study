package exception.ex3;

import java.util.Scanner;

public class MainV3 {
	public static void main(String[] args) {
		//NetworkServiceV3_1 networkService = new NetworkServiceV3_1();
		NetworkServiceV3_2 networkService = new NetworkServiceV3_2();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("転送する文字： ");
			String input = scanner.nextLine();
			if (input.equals("exit")) {
				break;
			}
			networkService.sendMessage(input);
			System.out.println();
		}
		System.out.println("プログラムを終了します。");

	}
}

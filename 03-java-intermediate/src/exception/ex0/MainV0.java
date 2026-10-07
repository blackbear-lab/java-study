package exception.ex0;

import java.util.Scanner;

public class MainV0 {
	public static void main(String[] args) {
		NetworkServiceV0 networkService = new NetworkServiceV0();
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

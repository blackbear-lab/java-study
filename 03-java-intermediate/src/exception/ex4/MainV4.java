package exception.ex4;


import exception.ex4.exception.SendExceptionV4;

import java.util.Scanner;

public class MainV4 {
	public static void main(String[] args) {
		//NetworkServiceV4 networkService = new NetworkServiceV4();
		NetworkServiceV5 networkService = new NetworkServiceV5();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("転送する文字： ");
			String input = scanner.nextLine();
			if (input.equals("exit")) {
				break;
			}

			try {
				networkService.sendMessage(input);
			} catch (Exception e) {
				exceptionHandler(e);
			}
			System.out.println();
		}
		System.out.println("プログラムを終了します。");

	}

	//共通例外処理
	private static void exceptionHandler(Exception e) {
		//共通処理
		System.out.println("ユーザーメッセージ： 申し訳ございません。予期せぬ、障害が発生しました。");
		System.out.println("==開発者向けデバッグメッセージ==");
		e.printStackTrace(System.out); //スタックトレース出力
		//e.printStackTrace();

		//必要であれば、例外別に追加処理可能
		if (e instanceof SendExceptionV4 sendEx) {
			System.out.println("[転送エラー] 伝送データ： " + sendEx.getSendData());
		}
	}
}

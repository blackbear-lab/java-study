package lang.system;

import java.util.Arrays;

public class SystemMain {

	public static void main(String[] args) {
		// 現在の時間を(ミリ秒)表示
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println("currentTimeMillis = " + currentTimeMillis);

		// 現在の時間を(ナノ秒)表示
		long currentTimeNano = System.nanoTime();
		System.out.println("currentTimeNano = " + currentTimeNano);

		// 環境変数を読み取る
		System.out.println(System.getenv());

		// システム属性を読み取る
		System.out.println("properties = " + System.getProperties());
		System.out.println("Java version: " + System.getProperty("java.version"));

		// 配列を高速でコピーする
		char[] originalArray = {'h', 'e', 'l', 'l', 'o'};
		char[] copiedArray = new char[5];
		System.arraycopy(originalArray, 0, copiedArray, 0, originalArray.length);

		// 配列出力
		System.out.println("copiedArray = " + copiedArray);    // copiedArray = [C@506e1b77
		// [=配列、C=char
		System.out.println("copiedArray = " + Arrays.toString(copiedArray));    // copiedArray = [C@506e1b77

		// プログラム終了
		System.exit(0);
	}
}

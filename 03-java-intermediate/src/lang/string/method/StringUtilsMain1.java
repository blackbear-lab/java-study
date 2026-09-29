package lang.string.method;

public class StringUtilsMain1 {

	public static void main(String[] args) {
		int num = 100;
		boolean bool = true;
		Object obj = new Object();
		String str = "Hello, Java!";

		// valueOfメソッド
		String numString = String.valueOf(num);
		System.out.println("数字の文字列値： " + numString);
		String boolString = String.valueOf(bool);
		System.out.println("ブーリアンの文字列値： " + boolString);
		String objString = String.valueOf(obj);
		System.out.println("オブジェクトの文字列値： " + objString);

		// 文字 + x -> 文字
		String numString2 = "" + num;
		System.out.println("空文字列 + num: " + numString2);

		// toCharArrayメソッド
		char[] strCharArray = str.toCharArray();
		System.out.println("文字列を文字の配列に変換： " + strCharArray);
		for (char c : strCharArray) {
			System.out.print(c);
		}
		System.out.println();
	}
}

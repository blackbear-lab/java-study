package lang.string.method;

public class StringInfoMain {

	public static void main(String[] args) {

		String str = "Hello, Java!";
		System.out.println("文字列の長さ： " + str.length());
		System.out.println("文字列に何もいないか1： " + str.isEmpty());
		System.out.println("文字列に何もいないか2： " + "".isEmpty());
		System.out.println("文字列に何もいないか、空白1： " + str.isBlank());
		System.out.println("文字列に何もいないか、空白2： " + "         ".isBlank());

		char c = str.charAt(7);
		System.out.println("7番インデックスの文字＝ " + c);

	}
}

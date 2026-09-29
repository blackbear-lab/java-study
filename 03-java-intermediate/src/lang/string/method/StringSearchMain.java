package lang.string.method;

public class StringSearchMain {

	public static void main(String[] args) {
		String str = "Hello, Java! Welcome to Java World";

		System.out.println("文字列に'Java'が含まれているか確認： " + str.contains("Java"));
		System.out.println("'Java'の初めてのインデックス： " + str.indexOf("Java"));
		System.out.println("インデックス10から'Java'のインデックス： " + str.indexOf("Java", 10));
		System.out.println("'Java'の最後のインデックス： " + str.lastIndexOf("Java"));
	}
}

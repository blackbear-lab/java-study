package lang.string.method;

public class StringChangeMain1 {

	public static void main(String[] args) {
		String str = "Hello, Java! Welcome to Java";

		System.out.println("インデックス7からの部分文字列： " + str.substring(7));
		System.out.println("インデックス7から12までの文字列： " + str.substring(7, 12));

		System.out.println("文字列結合： " + str.concat("!!!"));

		System.out.println("'Java'を'World'に変換： " + str.replace("Java", "World"));
		System.out.println("1番目の'Java'を'World'に変換： " + str.replaceFirst("Java", "World"));
	}
}

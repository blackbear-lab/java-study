package lang.string.method;

public class StringChangeMain2 {

	public static void main(String[] args) {
		String strWithSpaces = "	Java Programming ";

		System.out.println("小文字に変換： " + strWithSpaces.toLowerCase());
		System.out.println("大文字に変換： " + strWithSpaces.toUpperCase());

		System.out.println("空白削除(trim): '" + strWithSpaces.trim() + "'");
		System.out.println("空白削除(strip): '" + strWithSpaces.strip() + "'");
		System.out.println("前の空白削除(strip): '" + strWithSpaces.stripLeading() + "'");
		System.out.println("後の空白削除(strip): '" + strWithSpaces.stripTrailing() + "'");

	}
}

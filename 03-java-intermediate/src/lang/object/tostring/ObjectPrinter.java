package lang.object.tostring;

public class ObjectPrinter {
	public static void print(Object obj) {
		String string = "オブジェクト情報出力" + obj.toString();
		System.out.println(string);
	}
}

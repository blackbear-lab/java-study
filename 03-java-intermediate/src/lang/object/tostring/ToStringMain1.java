package lang.object.tostring;

public class ToStringMain1 {

	public static void main(String[] args) {
		Object object = new Object();
		String string = object.toString();

	//	toString() 値出力
		System.out.println(string);

	//	object 直接出力
		System.out.println(object);
	}
}

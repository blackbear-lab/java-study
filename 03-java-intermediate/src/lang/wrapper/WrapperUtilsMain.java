package lang.wrapper;

public class WrapperUtilsMain {

	public static void main(String[] args) {
		Integer i1 = Integer.valueOf(10);	// 数字、ラッパーオブジェクト変換
		Integer i2 = Integer.valueOf("10");	// 文字列、ラッパーオブジェクト変換
		int intValue = Integer.parseInt("10");	// 文字列専用、基本形変換

		// 比較
		int compareResult = i1.compareTo(20);
		System.out.println("compareResult = " + compareResult);

		// 算術演算
		System.out.println("sum: " + Integer.sum(10, 20));
		System.out.println("min: " + Integer.min(10, 20));
		System.out.println("max: " + Integer.max(10, 20));

	}
}

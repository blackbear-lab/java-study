package lang.wrapper.test;

public class WrapperTest1 {
	public static void main(String[] args) {
		String str1 = "10";
		String str2 = "20";
		// コード作成

		int i1 = Integer.parseInt(str1);
		int i2 = Integer.parseInt(str2);

		int result = i1 + i2;
		System.out.println("二つの数字の合計： " + result);
	}
}

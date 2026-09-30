package lang.wrapper.test;

public class WrapperTest3 {
	public static void main(String[] args) {
		String str = "100";
		//コード作成
		//String -> Integer
		Integer Integer1 = Integer.valueOf(str);
		System.out.println("IntegerStr = " + Integer1);

		//Integer -> int
		int intValue =Integer1.intValue();
		System.out.println("intValue = " + intValue);

		//int -> Integer
		Integer Integer2 = Integer.valueOf(intValue);
		System.out.println("Integer2 = " + Integer2);
	}
}

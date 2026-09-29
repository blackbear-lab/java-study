package lang.string.test;

public class StringTest10 {

	public static void main(String[] args) {
		String fruits = "apple,banana,mango";
		// コード作成
		String[] split = fruits.split(",");
		for (String s : split) {
			System.out.println(s);
		}
		String joinedString = String.join("->",split);
		System.out.println(joinedString);
	}
}

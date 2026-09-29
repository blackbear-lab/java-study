package lang.string.test;

public class TestString11 {
	public static void main(String[] args) {
		String str = "Hello Java";
		//コード作成
		StringBuilder sb = new StringBuilder(str).reverse();
		System.out.println(sb);
	}
}

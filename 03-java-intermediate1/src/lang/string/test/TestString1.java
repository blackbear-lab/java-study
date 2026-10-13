package lang.string.test;

public class TestString1 {

	public static void main(String[] args) {
		String url = "https://www.example.com";
		//コード作成
		boolean result = url.startsWith("https://");
		System.out.println("result = " + result);
	}
}

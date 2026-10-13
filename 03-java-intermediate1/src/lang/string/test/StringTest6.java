package lang.string.test;

public class StringTest6 {

	public static void main(String[] args) {
		String str = "start hello java, hello spring, hello jpa";
		String key = "hello";

		//コード作成
		int count = 0;
		int index = str.indexOf(key);

		while (index >= 0) {
			index = str.indexOf(key, index + 1);
			count++;
		}
		System.out.println("count = " + count);
	}
}

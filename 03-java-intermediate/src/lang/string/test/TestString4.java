package lang.string.test;

public class TestString4 {

	public static void main(String[] args) {
		String str = "hello.txt";
		//コード作成
		int index = str.indexOf(".txt");
		String filename = str.substring(0,index);
		String extName = str.substring(index);

		System.out.println("filename = " + filename);
		System.out.println("extName = " + extName);
	}

}

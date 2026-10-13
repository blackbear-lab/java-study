package lang.string.test;

public class TestString2 {

	public static void main(String[] args) {
		String[] arr = {"hello", "java", "jvm", "spring", "jpa"};
		// コード作成
		int hello = arr[0].length();
		int java = arr[1].length();
		int jvm = arr[2].length();
		int spring = arr[3].length();
		int jpa = arr[4].length();

		int sum = hello + java + jvm + spring + jpa;


		System.out.println("hello = " + hello);
		System.out.println("java = " + java);
		System.out.println("jvm = " + jvm);
		System.out.println("spring = " + spring);
		System.out.println("jpa = " + jpa);
		System.out.println("sum = " + sum);

		//解答
		int sum1 = 0;
		for (String s : arr) {
			System.out.println(s + ":" + s.length());
			sum1 += s.length();
		}
		System.out.println("sum1 = " + sum1);


	}
}

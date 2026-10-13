package generic.ex1;

public class BoxMain2 {

	public static void main(String[] args) {
		ObjectBox integerBox = new ObjectBox();
		integerBox.set(10);
		Integer integer = (Integer) integerBox.get();
		System.out.println("integer = " + integer);

		ObjectBox stringBox = new ObjectBox();
		stringBox.set("hello");
		String str = (String) stringBox.get();
		System.out.println("str = " + str);

		// 誤ったタイプの引数を渡す場合
		integerBox.set("文字1000");
		Integer result = (Integer) integerBox.get();
		System.out.println("result = " + result);
	}
}



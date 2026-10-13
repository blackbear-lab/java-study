package generic.ex1;

public class BoxMain3 {

	public static void main(String[] args) {

		GenericBox<Integer> integerBox = new GenericBox<Integer>();	//生成時、Tのタイプを決める。
		integerBox.set(10);
		//integerBox.set("文字100");	//Integerタイプのみ使用可能、コンパイルエラー発生
		Integer integer = integerBox.get(); // Integerタイプを戻す(キャスト不要)
		System.out.println("integer = " + integer);

		GenericBox<String> stringBox = new GenericBox<>();
		stringBox.set("hello");
		String str = stringBox.get();
		System.out.println("str = " + str);

		GenericBox<Double> doubleBox = new GenericBox<>();
		doubleBox.set(19.5);
		Double doubleValue = doubleBox.get();
		System.out.println("doubleValue = " + doubleValue);

		//タイプ推論： 生成するジェネリックタイプ省略可能
		GenericBox<Integer> integerBox2 = new GenericBox<>();
	}
}

package nested.anonymous.ex;

public class Ex0RefMain {

	public static void hello(String str) {
		System.out.println("プログラム開始");	//変わらないところ
		System.out.println(str);	//変わるところ
		System.out.println("プログラム終了");	//変わらないところ
	}

	public static void main(String[] args) {
		hello("Hello Java");
		hello("Hello Spring");
	}

}

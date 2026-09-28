package lang.immutable.address;

public class PrimitiveMain {

	public static void main(String[] args) {
	//	基本形は絶対に同じ値を共有しない。
		int a = 10;
		int b = a;
		System.out.println("a = " + a);
		System.out.println("b = " + b);

		b = 20;
		System.out.println("20 -> b");
		System.out.println("a = " + a);
		System.out.println("b = " + b);

	}
}

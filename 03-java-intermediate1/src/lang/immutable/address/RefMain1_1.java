package lang.immutable.address;

public class RefMain1_1 {

	public static void main(String[] args) {
		//   参照型変数は一つのインスタンスを共有できる。
		Address a = new Address("서울");
		Address b = a;
		System.out.println("a = " + a);
		System.out.println("b = " + b);

		b.setValue("부산");
		System.out.println("부산 -> b");
		System.out.println("a = " + a);
		System.out.println("b = " + b);

	}
}

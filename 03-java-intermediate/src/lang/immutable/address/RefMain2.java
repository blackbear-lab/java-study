package lang.immutable.address;

public class RefMain2 {

	public static void main(String[] args) {
		//参照型変数は一つのインスタンスを共有できる。
		ImmutableAddress a = new ImmutableAddress("서울");
		ImmutableAddress b = a;
		System.out.println("a = " + a);
		System.out.println("b = " + b);

		//b.setValue("부산"); //bの値を変更しないといけない。
		b = new ImmutableAddress("부산");
		System.out.println("부산 -> b");
		System.out.println("a = " + a);
		System.out.println("b = " + b);

	}
}

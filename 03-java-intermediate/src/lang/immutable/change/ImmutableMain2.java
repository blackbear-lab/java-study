package lang.immutable.change;

public class ImmutableMain2 {

	public static void main(String[] args) {
		ImmutableObj obj1 = new ImmutableObj(10);
		obj1.add(20);
		//オブジェクトを新しく生成して値を返しているため、値を代入しなければいけない。
		System.out.println("obj1 = " + obj1.getValue());
	}
}

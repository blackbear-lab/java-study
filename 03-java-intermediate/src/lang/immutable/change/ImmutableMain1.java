package lang.immutable.change;

public class ImmutableMain1 {

	public static void main(String[] args) {
		ImmutableObj obj1 = new ImmutableObj(10);
		ImmutableObj obj2 = obj1.add(20);

		//計算後、既存の値と新規の値を確認可能
		System.out.println("obj1 = " + obj1.getValue());
		System.out.println("obj2 = " + obj2.getValue());
	}
}

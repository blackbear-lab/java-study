package lang.immutable.change;

public class MutableMain {

	public static void main(String[] args) {
		MutableObj obj = new MutableObj(10);

		obj.add(20);

		//計算後、既存の値は消える。
		System.out.println("obj = " + obj.getValue());
	}
}

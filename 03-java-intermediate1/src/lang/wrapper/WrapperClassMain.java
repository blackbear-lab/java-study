package lang.wrapper;

public class WrapperClassMain {

	public static void main(String[] args) {
		Integer newInteger = new Integer(10); //未来に削除予定、代わりにvalueOfを使用推薦
		//Integer newInteger = Integer.valueOf(10);	//x001	//-128 ~ 127 よく使われる数字の値再使用、不変
		Integer integerObj = Integer.valueOf(10);	//x001	//-128 ~ 127 よく使われる数字の値再使用、不変
		Long longObj = Long.valueOf(100);
		Double doubleObj = Double.valueOf(10.5);

		System.out.println("newInteger = " + newInteger);
		System.out.println("integerObj = " + integerObj);
		System.out.println("longObj = " + longObj);
		System.out.println("doubleObj = " + doubleObj);
		System.out.println();

		System.out.println("内部の値");
		int intValue = integerObj.intValue();
		System.out.println("intValue = " + intValue);
		Long longValue = longObj.longValue();
		System.out.println("longValue = " + longValue);
		System.out.println();

		System.out.println("比較");
		System.out.println("==: " + (newInteger == integerObj));
		System.out.println("equals: " + (newInteger.equals(integerObj)));
	}
}

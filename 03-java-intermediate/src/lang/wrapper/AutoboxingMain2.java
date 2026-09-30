package lang.wrapper;

public class AutoboxingMain2 {

	public static void main(String[] args) {
		//primitive -> Wrapper
		int value = 7;
		Integer boxedValue = value;	//オートバッシング（Auto-boxing）

		// Wrapper -> Primitive
		int unboxedValue = boxedValue; //オートアンバッシング（Auto-Unboxing）

		System.out.println("boxedValue = " + boxedValue);
		System.out.println("unboxedValue = " + unboxedValue);
	}
}

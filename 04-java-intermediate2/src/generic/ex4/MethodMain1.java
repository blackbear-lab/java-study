package generic.ex4;

public class MethodMain1 {

	public static void main(String[] args) {
		Integer i = 10;
		Object object = GenericMethod.objectMethod(i);

		//タイプ引数(型引数)明示的伝達
		System.out.println("明示的、型引数伝達");
		Integer result = GenericMethod.<Integer>genericMethod(i);
		Integer integerValue = GenericMethod.<Integer>numberMethod(10);
		Double doubleValue = GenericMethod.<Double>numberMethod(20.1);

		System.out.println("タイプ推論");
		Integer result2 = GenericMethod.genericMethod(i);
		Integer resultValue2 = GenericMethod.numberMethod(10);
		Double doubleValue2 = GenericMethod.numberMethod(20.0);
	}
}

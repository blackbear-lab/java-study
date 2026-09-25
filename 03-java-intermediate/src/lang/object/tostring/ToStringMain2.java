package lang.object.tostring;

public class ToStringMain2 {

	public static void main(String[] args) {
		Car car = new Car("Model Y");
		Dog dog1 = new Dog("ワンころ１", 2);
		Dog dog2 = new Dog("ワンころ2", 5);


		System.out.println("1.単純 toString 呼出");
		System.out.println(car.toString());
		System.out.println(dog1.toString());
		System.out.println(dog2.toString());

		System.out.println("2.println 内部から toString 呼出");
		System.out.println(car);
		System.out.println(dog1.toString());
		System.out.println(dog2.toString());

		System.out.println("1.object 多態性活用");
		ObjectPrinter.print(car);
		ObjectPrinter.print(dog1);
		ObjectPrinter.print(dog2);

		String refValue1 = Integer.toHexString(System.identityHashCode(dog1));
		System.out.println(refValue1);

		String refValue2 = Integer.toHexString(System.identityHashCode(dog2));
		System.out.println(refValue2);
	}
}


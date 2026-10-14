package generic.ex4;

import generic.animal.Cat;
import generic.animal.Dog;

public class MethodMain3 {
	public static void main(String[] args) {
		Dog dog = new Dog("わんこ", 100);
		Cat cat = new Cat("にゃんこ", 50);

		ComplexBox<Dog> hospital = new ComplexBox<>();
		hospital.set(dog);

		Cat returnCat = hospital.printAndReturn(cat);
		System.out.println("returnCat = " + returnCat);
	}
}

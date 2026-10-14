package generic.ex4;

import generic.animal.Cat;
import generic.animal.Dog;

public class MethodMain2 {
	public static void main(String[] args) {

		Dog dog = new Dog("わんこ", 100);
		Cat cat = new Cat("にゃんこ", 100);

		AnimalMethod.checkUp(dog);
		AnimalMethod.checkUp(cat);

		Dog targetDog = new Dog("デカワンコ", 200);
		Dog bigger = AnimalMethod.bigger(dog, targetDog);
		System.out.println("bigger = " + bigger);
	}
}

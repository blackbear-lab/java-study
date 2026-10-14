package generic.ex5;

import generic.animal.Animal;
import generic.animal.Cat;
import generic.animal.Dog;

public class WIldCardMain2 {

	public static void main(String[] args) {
		Box<Object> objBox = new Box<>();
		Box<Animal> animalBox = new Box<>();
		Box<Dog> dogBox = new Box<>();
		Box<Cat> catBox = new Box<>();

		// Animalより上位タイプのみ使用可能
		writeBox(objBox);
		writeBox(animalBox);
		//writeBox(dogBox);
		//writeBox(catBox);


		Animal animal = animalBox.get();
		System.out.println("animal = " + animal);
	}

	static void writeBox(Box<? super Animal> box) {
		box.set(new Dog("わんこ", 100));
	}
}

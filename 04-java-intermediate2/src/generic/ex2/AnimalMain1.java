package generic.ex2;

import generic.animal.Animal;
import generic.animal.Cat;
import generic.animal.Dog;

public class AnimalMain1 {

	public static void main(String[] args) {
		Animal animal = new Animal("動物", 0);
		Dog dog = new Dog("わんこ", 100);
		Cat cat = new Cat("にゃんころ", 50);

		Box<Dog> dogBox = new Box<>();
		dogBox.set(dog);
		Dog findDog = dogBox.get();
		System.out.println("findDog = " + findDog);

		Box<Cat> catBox = new Box<>();
		catBox.set(cat);
		Cat findCat = catBox.get();
		System.out.println("findCat = " + findCat);

		Box<Animal> animalBox = new Box<>();
		animalBox.set(animal);
		Animal findAnimal = animalBox.get();
		System.out.println("findAnimal = " + findAnimal);
	}
}

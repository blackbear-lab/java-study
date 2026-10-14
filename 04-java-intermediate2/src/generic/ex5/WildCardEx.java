package generic.ex5;

import generic.animal.Animal;

public class WildCardEx {

	static <T> void printGenericV1(Box<T> box) {
		System.out.println("T = " + box.get());
	}

	//Box<Dog>, Box<Cat>, Box<Object>
	static void printWildcardV1(Box<?> box) {
		System.out.println("? = " + box.get());
	}

	static <T extends Animal> void printGenericV2(Box<T> box) {
		T t = box.get();
		System.out.println("名前 = " + t.getName());
	}

	static void printWildCardV2(Box<? extends Animal> box) {
		Animal animal = box.get();
		System.out.println("名前 = " + animal.getName());
	}

	static <T extends Animal> T printAndReturnGeneric(Box<T> box) {
		T t = box.get();
		System.out.println("名前 = " + t.getName());
		return t;
	}

	static Animal printAndReturnWildcard(Box<? extends Animal> box) {
		Animal animal = box.get();
		System.out.println("名前 = " + animal.getName());
		return animal;
	}
}

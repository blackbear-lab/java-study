package generic.ex3;

import generic.animal.Cat;

public class CatHospital {
	private Cat animal;

	public void set(Cat animal) {
		this.animal = animal;
	}

	public void checkup() {
		System.out.println("動物の名前 = " + animal.getName());
		System.out.println("動物の大きさ = " + animal.getSize());
		animal.sound();
	}

	public Cat bigger(Cat target) {
		return animal.getSize() > target.getSize() ? animal : target;
	}
}

package generic.ex3;

import generic.animal.Animal;

public class AnimalHospitalV3<T extends Animal> {
	//ジェネリクスを制限することができる
	//extendsを使用することでAnimalと子クラスのみ、ジェネリクスにはいれるようになれる。
	//ジェネリクスが制限されることでメソッドも使用できるようになる
	private T animal;


	public void set(T animal) {
		this.animal = animal;
	}

	public void checkup() {
		//Tのタイプを定義する時点ではTのメソッドをわからない。オブジェクトの機能のみ使用可能
		System.out.println("動物の名前 = " + animal.getName());
		System.out.println("動物のサイズ = " + animal.getSize());
		animal.sound();
	}

	public T bigger(T target) {
		return animal.getSize() > target.getSize() ? animal : target;
	}
}

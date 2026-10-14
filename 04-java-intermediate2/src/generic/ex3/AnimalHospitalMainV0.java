package generic.ex3;

import generic.animal.Cat;
import generic.animal.Dog;

public class AnimalHospitalMainV0 {

	public static void main(String[] args) {
		DogHospital dogHospital = new DogHospital();
		CatHospital catHospital = new CatHospital();

		Dog dog = new Dog("ワンころ１", 100);
		Cat cat = new Cat("にゃんころ1", 300);

		//犬の病院
		dogHospital.set(dog);
		dogHospital.checkup();

		//猫の病院
		catHospital.set(cat);
		catHospital.checkup();

		//問題1：犬の病院に猫を渡す
		//dogHospital.set(cat);	//別のタイプを入力するとコンパイルエラー発生

		//問題2：犬のタイプ戻す
		dogHospital.set(dog);
		Dog biggerDog = dogHospital.bigger(new Dog("わんこロ2", 200));
		System.out.println("biggerDog = " + biggerDog);
	}
}

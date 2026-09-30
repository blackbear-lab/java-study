package lang.math;

import java.util.Random;

public class RandomMain {
	public static void main(String[] args) {
		Random random = new Random();
		//Random random = new Random(100);	//seedが同じであればRandomの結果も同じだ

		int randomInt = random.nextInt();
		System.out.println("randomInt = " + randomInt);

		double randomDouble = random.nextDouble();
		System.out.println("randomDouble = " + randomDouble);

		boolean randomBoolean = random.nextBoolean();
		System.out.println("randomBoolean = " + randomBoolean);

		// 範囲照会
		int randomRange1 = random.nextInt(10);	// 0~9
		System.out.println("0 ~ 9 : " + randomRange1);

		int randomRange2 = random.nextInt(10) + 1;	// 1~10
		System.out.println("1 ~ 10 : " + randomRange2);
	}
}

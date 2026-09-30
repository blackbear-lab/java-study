package lang.math.test;

import java.util.Random;

public class LottoGenerator {

	private final Random random = new Random();
	private int[] lottoNumbers;
	private int count;

	public int[] generate() {
		lottoNumbers = new int[6];
		count = 0;

		while (count < 6) {
			//1から45間の数生成
			int number = random.nextInt(45) + 1;
			//中腹ではない場合のみ配列追加
			if (isUnique(number)) {
				lottoNumbers[count] = number;
				count++;
			}
		}
		return lottoNumbers;
	}

	private boolean isUnique(int number) {
		for (int i = 0; i < count; i++) {
			if (lottoNumbers[i] == number) {
				return false;
			}
		}
		return true;
	}
}

package lang.math.test;

public class LottoGeneratorMain {
	public static void main(String[] args) {
		LottoGenerator lottoGenerator = new LottoGenerator();
		int[] lottoNumbers = lottoGenerator.generate();

		System.out.print("lotto番号： ");
		for (int lottoNumber : lottoNumbers) {
			System.out.print(lottoNumber + " ");
		}
	}
}

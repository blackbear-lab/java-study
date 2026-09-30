package lang.wrapper;

public class WrapperVsPrimitive {

	public static void main(String[] args) {
		int iterations = 1_000_000_000;    //繰り返す回数
		long startTime, endTime;

		//基本形long使用
		long sumPrimitive = 0;
		startTime = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++) {
			sumPrimitive += i;
		}
		endTime = System.currentTimeMillis();
		System.out.println("sumPrimitive = " + sumPrimitive);
		System.out.println("基本資料系long実行時間： " + (endTime - startTime) + "ms");


		// ラッパークラスLong使用
		Long sumWrapper = 0L;
		startTime = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++) {
			sumWrapper += i;
		}
		endTime = System.currentTimeMillis();
		System.out.println("sumWrapper = " + sumWrapper);
		System.out.println("ラッパークラスLong実行時間： " + (endTime - startTime) + "ms");
	}
}

package lang.math;

public class MathMain {
	public static void main(String[] args) {
		//　基本演算メソッド
		System.out.println("max(10, 20): " + Math.max(10, 20));	// 最大値
		System.out.println("min(10, 20): " + Math.min(10, 20));	// 最小値
		System.out.println("abs(-10): " + Math.abs(-10));	// 絶対値

		// 四捨五入(ししゃごにゅう)と制度に関するメソッド
		System.out.println("ceil(2.1): " + Math.ceil(2.1));	// 切り上げ
		System.out.println("floor(2.1): " + Math.floor(2.1));	// 切り捨て
		System.out.println("round(2.1): " + Math.round(2.5));    // 四捨五入

		// 他の有用なメソッド
		System.out.println("sqrt(4): " + Math.sqrt(4));	// 平方根
		System.out.println("random(): " + Math.random());	//0.0 ~ 0.1間のdoubleの値

	}
}

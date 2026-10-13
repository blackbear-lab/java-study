package nested.nested;

public class NestedOuter {

	private static int outClassValue = 3;
	private int outInstanceValue = 2;
	String o = "11";

	static class Nested {
		private int nestedInstanceValue = 1;

		public void print() {
			//　自分のメンバーにアクセス
			System.out.println(nestedInstanceValue);

			// クラス外のインスタンスメンバーにはアクセスできない
			//System.out.println(outInstanceValue);

			// クラス外のクラスメンバーにはアクセス可能。privateもアクセス可能。
			System.out.println(outClassValue);
		}
	}

}

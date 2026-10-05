package nested.inner;

public class InnerOuter {

	private static int outClassValue = 3;
	private int outInstaceValue = 2;

	class Inner {
		private int innerInstanceValue = 1;

		public void print() {
			//自分自身に接近
			System.out.println(innerInstanceValue);

			//外部クラスのインスタンスにアクセス可能。privateもアクセス可能。
			System.out.println(outInstaceValue);

			//外部クラスのクラスメンバーにアクセス可能。privateもアクセス可能。
			System.out.println(InnerOuter.outClassValue);
		}
	}

}

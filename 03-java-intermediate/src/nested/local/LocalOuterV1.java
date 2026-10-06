package nested.local;

public class LocalOuterV1 {

	private int outInstanceVar = 3;
	//疑問点(staticの変数も使用可のであろうか)
	private static int ss = 4;

	public void process(int paramVar) {
		int localVar = 1;

		class LocalPrinter {
			int value = 0;

			public void printData() {
				System.out.println("value = " + value);
				System.out.println("localVar = " + localVar);
				System.out.println("paramVar = " + paramVar);
				System.out.println("outInstanceVar = " + outInstanceVar);
				//使用可能を確認
				System.out.println("ss = " + ss);
			}
		}
		LocalPrinter printer = new LocalPrinter();
		printer.printData();
	}

	public static void main(String[] args) {
		LocalOuterV1 localOuter = new LocalOuterV1();
		localOuter.process(2);
	}
}

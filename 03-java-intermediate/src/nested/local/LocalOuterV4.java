package nested.local;

import java.lang.reflect.Field;

public class LocalOuterV4 {

	private int outInstanceVar = 3;

	public Printer process(int paramVar) {
		int localVar = 1;	//地域変数はスタックフレームが終了される瞬間一緒に削除される。
		class LocalPrinter implements Printer{
			int value = 0;

			@Override
			public void print() {
				System.out.println("value = " + value);

				//インスタンス地域変数よりもっと長く生きている。
				System.out.println("localVar = " + localVar);
				System.out.println("paramVar = " + paramVar);
				System.out.println("outInstanceVar = " + outInstanceVar);
			}
		}
		LocalPrinter printer = new LocalPrinter();
		//printer.print();をここで実装せず、Printerインスタンス飲み戻す
		//もしlocalVarの値を変更する場合、もう一度キャプチャーをするのか？？
		//LocalPrinterを生成する時点で地域変数をキャプチャする
		//もし値を変更した際、キャプチャーしなおすように設計するとキャプチャー側と地域変数の値が違う場合どこが正であるか判断できない。
		//paramVar = 20:
		return printer;
	}

	public static void main(String[] args) {
		LocalOuterV4 localOuter = new LocalOuterV4();
		Printer printer = localOuter.process(2);

		//printer.pirnt()を後で実行する。process()のスタックフレーム消えた以降実行。
		printer.print();

		//追加
		System.out.println("フィールド確認");
		Field[] fields = printer.getClass().getDeclaredFields();
		for (Field field : fields) {
			System.out.println("field = " + field);
		}
	}
}

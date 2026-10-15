package collection.array;

public class MyArrayListV3BadMain {
	public static void main(String[] args) {
		MyArrayListV3 numberList = new MyArrayListV3();

		//数字のみ入力することを想定
		numberList.add(1);
		numberList.add(2);
		numberList.add("文字３");
		System.out.println(numberList);

		//Objectを戻すため、ダウンキャストが必要になる
		Integer num1 = (Integer) numberList.get(0);
		Integer num2 = (Integer) numberList.get(1);

		// ClassCastException発生、もじをIntegerにキャスト
		Integer num3 = (Integer) numberList.get(2);
	}
}

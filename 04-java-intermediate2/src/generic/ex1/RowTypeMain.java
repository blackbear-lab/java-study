package generic.ex1;

public class RowTypeMain {

	public static void main(String[] args) {
		GenericBox integerBox = new GenericBox();
		//GenericBox<Object> integerBox = new GenericBox<>(); // 推薦
		integerBox.set(10);
		Integer result = (Integer) integerBox.get();
		System.out.println("result = " + result);
	}
}

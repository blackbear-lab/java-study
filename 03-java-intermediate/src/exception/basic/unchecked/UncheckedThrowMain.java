package exception.basic.unchecked;

public class UncheckedThrowMain {
	public static void main(String[] args) {
		Service service = new Service();
		service.callThrow();
		System.out.println("正常終了");
	}
}

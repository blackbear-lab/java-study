package exception.basic.unchecked;

public class UncheckedCatchMain {
	public static void main(String[] args) {
		Service service = new Service();
		service.callCatch();
		System.out.println("正常終了");
	}
}

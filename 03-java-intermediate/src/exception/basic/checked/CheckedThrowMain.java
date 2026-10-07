package exception.basic.checked;

public class CheckedThrowMain {

	public static void main(String[] args) throws MyCheckedException {
		Service service = new Service();
		service.catchThrow();
		System.out.println("正常終了");
	}
}

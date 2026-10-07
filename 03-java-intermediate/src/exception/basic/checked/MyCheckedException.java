package exception.basic.checked;

/*
 * Exceptionを継承する例外はチェック例外になる
 * */
public class MyCheckedException extends Exception {

	public MyCheckedException(String message) {
		super(message);
	}
}

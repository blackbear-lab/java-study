package exception.basic.unchecked;

/*
* RuntimeExceptionを継承する例外は日検査例外になる
*/
public class MyUncheckedException extends RuntimeException{
	public MyUncheckedException(String message) {
		super(message);
	}
}

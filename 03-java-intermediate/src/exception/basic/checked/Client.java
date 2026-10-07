package exception.basic.checked;

public class Client {
	public void call() throws MyCheckedException{
		//　問題発生
		throw new MyCheckedException("ex");
	}
}

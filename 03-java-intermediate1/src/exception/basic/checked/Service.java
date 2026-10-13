package exception.basic.checked;

public class Service {
	Client client = new Client();

	/*
	 * 例外を掴んで処理するコード
	 */
	public void callCatch() {
		try {
			client.call();
		} catch (Exception e) {
			//例外処理ロジック
			System.out.println("例外処理, message=" + e.getMessage());
		}
		System.out.println("正常");
	}

	/*
	* チェック例外を投げるコード
	* チェック例外を処理せず、投げるならthrows例外をメソッドに必須で宣言しなければいけない
	*/

	public void catchThrow() throws MyCheckedException {
		client.call();
	}

}

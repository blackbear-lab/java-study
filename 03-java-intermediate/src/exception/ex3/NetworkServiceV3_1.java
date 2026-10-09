package exception.ex3;


import exception.ex3.exception.ConnectExceptionV3;
import exception.ex3.exception.SendExceptionV3;

public class NetworkServiceV3_1 {

	public void sendMessage(String data) {
		String address = "http://example.com";
		NetworkClientV3 client = new NetworkClientV3(address);
		client.initError(data);

		try {
			client.connect();
			client.send(data);
		} catch (ConnectExceptionV3 e) {
			System.out.println("[アクセスエラー]住所： " + e.getAddress() + ", メッセージ： " + e.getMessage());
		} catch (SendExceptionV3 e) {
			System.out.println("[転送エラー]コ転送データ： " + e.getSendData() + ", メッセージ： " + e.getMessage());
		} finally {
			client.disconnect();
		}
	}
}

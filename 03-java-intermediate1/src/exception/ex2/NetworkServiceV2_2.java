package exception.ex2;



public class NetworkServiceV2_2 {

	public void sendMessage(String data) {
		String address = "http://example.com";
		NetworkClientV2 client = new NetworkClientV2(address);
		client.initError(data);

		try {
			client.connect();
		} catch (NetworkClientExceptionV2 e) {
			System.out.println("[エラー]コード： " + e.getErrorCode() + ", メッセージ： " + e.getMessage());
			return;
		}
		try {
			client.send(data);
		} catch (NetworkClientExceptionV2 e) {
			System.out.println("[エラー]コード： " + e.getErrorCode() + ", メッセージ： " + e.getMessage());
			return;
		}
		client.disconnect();
	}
}

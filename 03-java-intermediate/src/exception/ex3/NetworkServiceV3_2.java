package exception.ex3;



public class NetworkServiceV3_2 {

	public void sendMessage(String data) {
		String address = "http://example.com";
		NetworkClientV3 client = new NetworkClientV3(address);
		client.initError(data);

		try {
			client.connect();
			client.send(data);
		} catch (ConnectExceptionV3 e) {
			System.out.println("[アクセスエラー]住所： " + e.getAddress() + ", メッセージ： " + e.getMessage());
		} catch (NetworkClientExceptionV3 e) {
			System.out.println("[ネットワークエラー] メッセージ： " + e.getMessage());
		} catch (Exception e) {
			System.out.println("[原因不明のエラ] メッセージ： "+ e.getMessage());
		}finally {
			client.disconnect();
		}
	}
}

package exception.ex4;



public class NetworkServiceV5 {

	public void sendMessage(String data) {
		String address = "http://example.com";

		try (NetworkClientV5 client = new NetworkClientV5(address)){
			client.initError(data);
			client.connect();
			client.send(data);
		} catch (Exception e) {
			System.out.println("[例外確認]： " + e.getMessage());
			throw e;
		}
	}
}

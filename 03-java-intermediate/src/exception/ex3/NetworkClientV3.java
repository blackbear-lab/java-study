package exception.ex3;


public class NetworkClientV3 {
	private final String address;
	public boolean connectError;
	public boolean sendError;

	public NetworkClientV3(String address) {
		this.address = address;
	}

	public void connect() throws ConnectExceptionV3 {
		if (connectError) {
			throw new ConnectExceptionV3(address, address + " サーバーアクセス失敗");
		}
		//アクセス成功
		System.out.println(address + "サーバーアクセス成功");
	}

	public void send(String data) throws SendExceptionV3 {
		if (sendError) {
			//throw new SendExceptionV3(data, address + "サーバーへデータ転送失敗: " + data);
			throw new RuntimeException("ex");
		}
		//転送成功
		System.out.println(address + "サーバーへデータ転送： " + data);
	}

	public void disconnect() {
		System.out.println(address + "サーバーアクセス解除");
	}

	public void initError(String data) {
		if (data.contains("error1")) {
			connectError = true;
		}
		if (data.contains("error2")) {
			sendError = true;
		}
	}
}

package exception.ex2;

public class NetworkClientV2 {
	private final String address;
	public boolean connectError;
	public boolean sendError;

	public NetworkClientV2(String address) {
		this.address = address;
	}

	public String connect() throws NetworkClientExceptionV2 {
		if (connectError) {
			throw new NetworkClientExceptionV2("connectError", address + "サーバーアクセス失敗");
		}
		//アクセス成功
		System.out.println(address + "サーバーアクセス成功");
		return "success";
	}

	public String send(String data) throws NetworkClientExceptionV2 {
		if (sendError) {
			//throw new NetworkClientExceptionV2("sendError", address + "サーバーへデータ転送失敗" + data);
			// 別の例がが発生した場合
			throw new RuntimeException("ex");
		}
		//転送成功
		System.out.println(address + "サーバーへデータ転送： " + data);
		return "success";
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

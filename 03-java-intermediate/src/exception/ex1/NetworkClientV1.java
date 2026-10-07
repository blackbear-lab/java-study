package exception.ex1;

public class NetworkClientV1 {
	private final String address;
	public boolean connectError;
	public boolean sendError;

	public NetworkClientV1(String address) {
		this.address = address;
	}

	public String connect() {
		if (connectError) {
			System.out.println(address + "サーバーアクセス失敗");
			return "connectError";
		}
		//アクセス成功
		System.out.println(address + "サーバーアクセス成功");
		return "success";
	}

	public String send(String data) {
		if (sendError) {
			System.out.println(address + "サーバーへデータ転送失敗" + data);
			return "sendError";
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

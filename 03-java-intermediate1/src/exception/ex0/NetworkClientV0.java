package exception.ex0;

public class NetworkClientV0 {
	private final String address;

	public NetworkClientV0(String address) {
		this.address = address;
	}

	public String connect() {
		//アクセス成功
		System.out.println(address + "サーバーアクセス成功");
		return "success";
	}

	public String send(String data) {
		//転送成功
		System.out.println(address + "サーバーへデータ転送： " + data);
		return "success";
	}

	public void disconnect() {
		System.out.println(address + "サーバーアクセス解除");
	}
}

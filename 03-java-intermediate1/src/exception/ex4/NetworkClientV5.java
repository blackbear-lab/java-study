package exception.ex4;


import exception.ex4.exception.ConnectExceptionV4;
import exception.ex4.exception.SendExceptionV4;

public class NetworkClientV5 implements AutoCloseable {
	private final String address;
	public boolean connectError;
	public boolean sendError;

	public NetworkClientV5(String address) {
		this.address = address;
	}

	public void connect() {
		if (connectError) {
			throw new ConnectExceptionV4(address, address + " サーバーアクセス失敗");
		}
		//アクセス成功
		System.out.println(address + "サーバーアクセス成功");
	}

	public void send(String data)  {
		if (sendError) {
			throw new SendExceptionV4(data, address + "サーバーへデータ転送失敗: " + data);
			//throw new RuntimeException("ex");
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

	@Override
	public void close() {
		System.out.println("NetworkClientV5.close");
		disconnect();
	}
}

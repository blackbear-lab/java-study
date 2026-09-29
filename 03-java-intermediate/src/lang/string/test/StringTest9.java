package lang.string.test;

public class StringTest9 {

	public static void main(String[] args) {
		String email = "hello@example.com";
		// コード作成
		String[] mail = email.split("@");
		System.out.println("ID = " + mail[0]);
		System.out.println("Domain = " + mail[1]);

		// 解答
		String[] parts = email.split("@");
		String idPart = parts[0];
		String domainPart = parts[1];
		System.out.println("ID: " + idPart);
		System.out.println("Domain: " + domainPart);
	}
}

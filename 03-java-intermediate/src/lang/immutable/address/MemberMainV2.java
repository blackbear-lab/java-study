package lang.immutable.address;

public class MemberMainV2 {

	public static void main(String[] args) {
		ImmutableAddress address = new ImmutableAddress("東京");

		MemberV2 memberA = new MemberV2("会員A", address);
		MemberV2 memberB = new MemberV2("会員B", address);

		//会員A、会員Bの最小の住所は東京
		System.out.println("memberA = " + memberA);
		System.out.println("memberB = " + memberB);

		//会員Bの住所を大阪に変更しなければいけない。
		//memberB.getAddress().setAddress("大阪"); //コンパイルエラー
		memberB.setAddress(new ImmutableAddress("大阪"));
		System.out.println("大阪 → memberB.address");
		System.out.println("memberA = " + memberA);
		System.out.println("memberB = " + memberB);
	}
}

package lang.immutable.address;

public class MemberMainV1 {

	public static void main(String[] args) {
		Address address = new Address("東京");

		MemberV1 memberA = new MemberV1("会員A", address);
		MemberV1 memberB = new MemberV1("会員B", address);

		//会員A、会員Bの最小の住所は東京
		System.out.println("memberA = " + memberA);
		System.out.println("memberB = " + memberB);

		//会員Bの住所を大阪に変更しなければいけない。
		memberB.getAddress().setValue("大阪");
		//会員Aも大阪に変更される問題が生じる。

		System.out.println("memberA = " + memberA);
		System.out.println("memberB = " + memberB);
	}
}

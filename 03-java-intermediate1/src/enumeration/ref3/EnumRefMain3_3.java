package enumeration.ref3;


import static enumeration.ref3.Grade.*;

public class EnumRefMain3_3 {

	public static void main(String[] args) {
		int price = 10000;

		prindDiscount(BASIC, price);
		prindDiscount(GOLD, price);
		prindDiscount(DIAMOND, price);
	}

	private static void prindDiscount(Grade grade, int price) {
		System.out.println(grade.name() + "等級による割引金額" + grade.discount(price));
	}
}

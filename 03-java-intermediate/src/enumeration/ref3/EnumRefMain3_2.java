package enumeration.ref3;


import static enumeration.ref3.Grade.*;

public class EnumRefMain3_2 {

	public static void main(String[] args) {
		int price = 10000;

		System.out.println("BASICの割引金額： "+ BASIC.discount(price));
		System.out.println("GOLDの割引金額： "+ BASIC.discount(price));
		System.out.println("DIAMONDの割引金額： "+ BASIC.discount(price));
	}
}

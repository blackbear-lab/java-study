package enumeration.ref3;


import static enumeration.ref3.Grade.*;

public class EnumRefMain3_1 {

	public static void main(String[] args) {
		int price = 10000;

		DiscountService discountService = new DiscountService();
		int basic = discountService.discount(BASIC, price);
		int gold = discountService.discount(GOLD, price);
		int diamond = discountService.discount(DIAMOND, price);

		System.out.println("BASICの割引金額： "+ basic);
		System.out.println("GOLDの割引金額： "+ gold);
		System.out.println("DIAMONDの割引金額： "+ diamond);
	}
}

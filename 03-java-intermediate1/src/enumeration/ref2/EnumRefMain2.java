package enumeration.ref2;


import enumeration.ex3.DiscountService;

import static enumeration.ex3.Grade.*;

public class EnumRefMain2 {

	public static void main(String[] args) {
		int price = 10000;

		enumeration.ex3.DiscountService discountService = new DiscountService();
		int basic = discountService.discount(BASIC, price);
		int gold = discountService.discount(GOLD, price);
		int diamond = discountService.discount(DIAMOND, price);

		System.out.println("BASICの割引金額： "+ basic);
		System.out.println("GOLDの割引金額： "+ gold);
		System.out.println("DIAMONDの割引金額： "+ diamond);
	}
}

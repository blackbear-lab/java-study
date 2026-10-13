package enumeration.ex1;


public class StringGradeEx1_1 {

	public static void main(String[] args) {
		int price = 10000;

		DiscountService discountService = new DiscountService();
		int basic = discountService.discount(StringGrade.BASIC, price);
		int gold = discountService.discount(StringGrade.GOLD, price);
		int diamond = discountService.discount(StringGrade.DIAMOND, price);

		System.out.println("BASICの割引金額： "+ basic);
		System.out.println("GOLDの割引金額： "+ gold);
		System.out.println("DIAMONDの割引金額： "+ diamond);
	}
}

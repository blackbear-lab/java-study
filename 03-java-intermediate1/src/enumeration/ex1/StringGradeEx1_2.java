package enumeration.ex1;


public class StringGradeEx1_2 {

	public static void main(String[] args) {
		int price = 10000;

		DiscountService discountService = new DiscountService();

		// 定数で判断する場合の問題点
		// 文字列で判断する場合とあまり変わらない
		// 存在しない文字入力
		int vip = discountService.discount("VIP", price);

		System.out.println("VIPの割引金額： "+ vip);

		// 誤字を入力
		int diamondd = discountService.discount("DIAMONDD", price);
		System.out.println("DIAMONDDの割引金額 = " + diamondd);

		// 小文字入力
		int gold = discountService.discount("gold", price);
		System.out.println("goldの割引金額 = " + gold);

	}
}

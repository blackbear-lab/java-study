package collection.list.test.ex2;

import java.util.ArrayList;

public class ShoppingCart {

	private int totalPrice;
	ArrayList<Item> items = new ArrayList<>();

	public void addItem(Item item) {
		items.add(item);
	}


	public void displayItems() {
		System.out.println("カートリスト出力");
		for (Item item : items) {
			System.out.println("商品名：" + item.getName() + ", 合計： " + item.getTotalPrice());
			totalPrice += item.getTotalPrice();
		}
		System.out.println("全商品の価格合計： " + totalPrice);
	}

}

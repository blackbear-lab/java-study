package collection.map.test.cart;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<Product, Integer> cartMap = new HashMap<>();

	public void add(Product product, int addQuantity) {
		Integer existingQuantity = cartMap.getOrDefault(product, 0);
		cartMap.put(product, existingQuantity + addQuantity);
	}

	public void minus(Product product, int minusQuantity) {
		int existingQuantity = cartMap.getOrDefault(product, 0);
		int newQuantity = existingQuantity - minusQuantity;
		if (newQuantity <= 0) {
			cartMap.remove(product);
		} else {
			cartMap.put(product, newQuantity);
		}
	}

	public void printAll() {
		System.out.println("すべての商品出力");
		for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
			System.out.println("商品： "+ entry.getKey() + "数量： " + entry.getValue());
		}
	}
	// コード作成
}

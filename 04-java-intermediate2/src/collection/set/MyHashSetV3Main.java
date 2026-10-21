package collection.set;

public class MyHashSetV3Main {

	public static void main(String[] args) {
		MySet<String> set = new MyHashSetV3<>(10);

		set.add("A");
		set.add("B");
		set.add("C");

		//検索
		String searchValue = "A";
		boolean result = set.contains(searchValue);
		System.out.println("set.contains(" + searchValue + ") = " + result);
	}
}

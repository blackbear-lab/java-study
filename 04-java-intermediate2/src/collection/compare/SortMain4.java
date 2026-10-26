package collection.compare;

import java.util.Collections;
import java.util.LinkedList;

public class SortMain4 {

	public static void main(String[] args) {
		MyUser myUser1 = new MyUser("a", 30);
		MyUser myUser2 = new MyUser("b", 20);
		MyUser myUser3 = new MyUser("c", 10);

		LinkedList<MyUser> list = new LinkedList<>();
		list.add(myUser1);
		list.add(myUser2);
		list.add(myUser3);

		System.out.println("基本データ");
		System.out.println(list);

		System.out.println("Comparable並べ替え");
		list.sort(null);
		//Collections.sort(list);
		System.out.println(list);

		System.out.println("IdComparator並べ替え");
		list.sort(new IdComparator());
		//Collections.sort(list, new IdComparator());
		System.out.println(list);
	}
}

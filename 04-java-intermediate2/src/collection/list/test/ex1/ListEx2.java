package collection.list.test.ex1;

import java.util.ArrayList;
import java.util.Scanner;

public class ListEx2 {

	public static void main(String[] args) {

		System.out.println("n個の整数を入力してください。(終了 0)");
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> arr = new ArrayList<>();

		while (true) {
			int i = sc.nextInt();
			if (i == 0) {

				break;
			}
			arr.add(i);
		}
		System.out.println("出力");
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(arr.get(i));
			if (i < arr.size() - 1) {
				System.out.print(", ");
			}
		}
	}


}

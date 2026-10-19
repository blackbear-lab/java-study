package collection.list.test.ex1;

import java.util.ArrayList;
import java.util.Scanner;

public class ListEx3 {
	public static void main(String[] args) {
		int total = 0;
		double average = 0.0;

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

		for (int i = 0; i < arr.size(); i++) {
			total += arr.get(i);
		}
		System.out.println("入力した整数の合計： " + total);
		average = (double) total / arr.size();
		System.out.println("入力した整数の平均： " + average);
	}
}

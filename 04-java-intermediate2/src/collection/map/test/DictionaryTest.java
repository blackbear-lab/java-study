package collection.map.test;

import java.util.HashMap;
import java.util.Scanner;

public class DictionaryTest {
	public static void main(String[] args) {
		System.out.println("==単語入力段階==");
		HashMap<String, String> dictionary = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("英語の単語を入力してください。(終了　'q'): ");
			String englishWord = sc.nextLine();
			if (englishWord.equals("q")) {
				break;
			}
				System.out.print("日本語の意味を入力してください： ");
				String japaneseWord = sc.nextLine();
				dictionary.put(englishWord, japaneseWord);

		}

		System.out.println("==単語検索段階==");
		while (true) {
			System.out.print("探す英単語を入力してください。(終了　'q'): ");
			String searchWord = sc.nextLine();
			if (searchWord.equals("q")) {
				break;
			}
			if (dictionary.containsKey(searchWord)) {
				System.out.println(searchWord + "の意味： " + dictionary.get(searchWord));
			} else {
				System.out.println(searchWord + "は辞書にそんざいしない単語です。");
			}
		}
	}
}

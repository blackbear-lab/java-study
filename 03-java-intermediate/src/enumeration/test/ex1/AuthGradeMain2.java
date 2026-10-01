package enumeration.test.ex1;

import java.util.Scanner;

import static enumeration.test.ex1.AuthGrade.*;

public class AutoGradeMain2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("等級を入力してください。[GUEST, LOGIN，ADMIN]: ");
		String grade = scanner.nextLine();

		AuthGrade authGrade = valueOf(grade.toUpperCase());
		System.out.println("あなたの等級は" + authGrade.getDescription() + "です。");
		System.out.println("==メニューリスト==");

		if (authGrade.getLevel() > 0) {
			System.out.println("- メイン画面");

		}else if (authGrade.getLevel() > 1) {
			System.out.println("- メール管理画面");
		} else if (authGrade.getLevel() > 2) {
			System.out.println("- 管理者画面");
		}
	}
}

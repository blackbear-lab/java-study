package time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormattingMain1 {
	public static void main(String[] args) {
		//フォーマッティング：日付を文字列に
		LocalDate date = LocalDate.of(2024, 12, 31);
		System.out.println("date = " + date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年 MM月 dd日");
		String formattedDate = date.format(formatter);
		System.out.println("日付と時間フォーマッティング = " + formattedDate);

		//パーシング： 文字列を日付に
		String input = "2030年 01月 01日";
		LocalDate parseDate = LocalDate.parse(input, formatter);
		System.out.println("文字列パーシング日付と時間 = " + parseDate);
	}
}


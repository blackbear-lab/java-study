package time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormattingMain2 {
	public static void main(String[] args) {
		//フォーマッティング：日付と時間を文字列に
		LocalDateTime now = LocalDateTime.of(2024, 12, 31, 13, 30, 59);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);
		System.out.println("formattedDateTime = " + formattedDateTime);

		//パーシング：文字列を日付と時間に
		String dateTimeString = "2030-01-01 11:30:00";
		LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);
		System.out.println("parsedDateTime = " + parsedDateTime);
	}
}

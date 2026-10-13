package time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeMain {
	public static void main(String[] args) {
		LocalDateTime nowDt = LocalDateTime.now();
		LocalDateTime ofDt = LocalDateTime.of(2016, 8, 16, 8, 10, 1);

		System.out.println("nowDt = " + nowDt);
		System.out.println("ofDt = " + ofDt);

		//日付と時刻の分離
		LocalDate localDate = ofDt.toLocalDate();
		LocalTime localTime = ofDt.toLocalTime();
		System.out.println("localDate = " + localDate);
		System.out.println("localTime = " + localTime);

		//日付と時刻の一体化
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		System.out.println("localDateTime = " + localDateTime);

		//計算(不変)
		LocalDateTime foDtPlus = ofDt.plusDays(1000);
		System.out.println("指定日付時間 + 1000d = " + foDtPlus);
		LocalDateTime ofDtPlus1Year = ofDt.plusYears(1);
		System.out.println("ofDtPlus1Year = " + ofDtPlus1Year);

		//比較
		System.out.println("現在日付と時間がしてされた日付と時刻より以前であるか？ " + nowDt.isBefore(ofDt));
		System.out.println("現在日付と時間がしてされた日付と時刻より以降であるか？ " + nowDt.isAfter(ofDt));
		System.out.println("現在日付と時間がしてされた日付と時刻より同じであるか？ " + nowDt.isEqual(ofDt));
		System.out.println("現在日付と時間がしてされた日付と時刻より同じであるか？ " + nowDt.equals(ofDt));
		//isEquals と equalsの差
		//isEquals: 比較対象の時間のみをひかく
		//equals: 時間だけではなくオブジェクトのタイプ、タイムゾーンなど、内部のすべて構成要素を比較する

	}
}

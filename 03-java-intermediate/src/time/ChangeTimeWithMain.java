package time;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class ChangeTimeWithMain {
	public static void main(String[] args) {
		LocalDateTime dt = LocalDateTime.of(2018, 1, 1, 13, 30, 59);
		System.out.println("dt = " + dt);

		LocalDateTime changedDt1 = dt.with(ChronoField.YEAR, 2020);
		System.out.println("changedDt1 = " + changedDt1);

		LocalDateTime changedDt2 = dt.withYear(2020);
		System.out.println("changedDt2 = " + changedDt2);

		//TemporalAdjuster 使用
		//来週金曜日
		LocalDateTime with1 = dt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		System.out.println("基準日付 = " + dt);
		System.out.println("来週金曜日の日付 = " + with1);

		//今月の最後日曜日
		LocalDateTime with2 = dt.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
		System.out.println("基準付きの最後日曜日の日付 = " + with2);
	}
}

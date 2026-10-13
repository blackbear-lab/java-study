package time.test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

public class TestBetween {
	public static void main(String[] args) {
		LocalDate startDate = LocalDate.of(2024, 1, 1);
		LocalDate endDate = LocalDate.of(2024, 11, 21);

		//コード作成(自分)
		/*
		Period between = Period.between(startDate, endDate);

		System.out.println("始まりの日付 = " + startDate);
		System.out.println("終わりの日付 = " + endDate);
		System.out.println("残りの期間 = " + between.getYears() + "年 " + between.getMonths() + "月 " + between.getDays() +
				"日");
		*/

		//答案
		Period period = Period.between(startDate, endDate);
		long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		System.out.println("始まりの日付 = " + startDate);
		System.out.println("終わりの日付 = " + endDate);
		System.out.println("残りの期間 = " + period.getYears() + "年 " + period.getMonths() + "月 " + period.getDays() +
				"日");
		System.out.println("D-day = 残り" + daysBetween + "日");

	}
}

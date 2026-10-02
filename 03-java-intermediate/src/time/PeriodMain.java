package time;

import java.time.LocalDate;
import java.time.Period;

public class PeriodMain {
	public static void main(String[] args) {
		//生成
		Period period = Period.ofDays(10);
		System.out.println("period = " + period);

		//計算に使用
		LocalDate currentDate = LocalDate.of(2030, 1, 1);
		LocalDate plusDate = currentDate.plus(period);
		System.out.println("currentDate = " + currentDate);
		System.out.println("plusDate = " + plusDate);

		//期間の差
		LocalDate startDate = LocalDate.of(2023, 1, 1);
		LocalDate endDate = LocalDate.of(2023, 4, 2);
		Period between = period.between(startDate, endDate);
		System.out.println("between = " + between);
		System.out.println("期間： " + between.getMonths() + "ヶ月 " + between.getDays() + "日");
	}
}

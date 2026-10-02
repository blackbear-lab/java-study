package time;

import java.time.Duration;
import java.time.LocalTime;

public class DurationMain {

	public static void main(String[] args) {
		Duration duration = Duration.ofMinutes(30);
		System.out.println("duration = " + duration);

		LocalTime lt = LocalTime.of(1, 0);
		System.out.println("lt = " + lt);

		//計算に使用
		LocalTime plusTime = lt.plus(duration);
		System.out.println("plusTime = " + plusTime);

		//時間の差
		LocalTime start = LocalTime.of(9, 0);
		LocalTime end = LocalTime.of(10, 0);
		Duration between = Duration.between(start, end);
		System.out.println("差： " + between.getSeconds() + "秒");
		System.out.println("勤務時間： " + between.toHours() + "時間" + between.toMinutesPart() + "分");
	}
}

package time.test;

import java.time.LocalDateTime;

public class TestPlus {
	public static void main(String[] args) {
		LocalDateTime dt = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
		System.out.println("基準時刻： " + dt);
		LocalDateTime plussedDateTime = dt.plusYears(1).plusMonths(2).plusDays(3).plusHours(4);
		System.out.println("1年 2ヶ月 3日 4時間後の時刻 = " + plussedDateTime);
	}
}

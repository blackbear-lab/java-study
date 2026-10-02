package time.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestZone {
	public static void main(String[] args) {
		ZonedDateTime tokyoTime = ZonedDateTime.of(LocalDate.of(2024, 1, 1), LocalTime.of(9, 0), ZoneId.of("Asia/Tokyo"
		));
		ZonedDateTime londonTime = tokyoTime.withZoneSameInstant(ZoneId.of("Europe/London"));
		ZonedDateTime nyTime = tokyoTime.withZoneSameInstant(ZoneId.of("America/New_York"));

		System.out.println("東京の会議時間 = " + tokyoTime);
		System.out.println("ロンドンの会議時間 = " + londonTime);
		System.out.println("ニューヨークの会議時間 = " + nyTime);
	}
}

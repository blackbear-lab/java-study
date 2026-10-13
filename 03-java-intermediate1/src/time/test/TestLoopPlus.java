package time.test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TestLoopPlus {
	public static void main(String[] args) {
		LocalDate startDate = LocalDate.of(2024, 1, 1);

		//自分の回答
		for (int i = 0; i < 5; i++) {
			if (i == 0) {
				System.out.println("日付 " + (i + 1) + ": " + startDate);
			} else {
				startDate = startDate.plusWeeks(2);
				System.out.println("日付 " + (i + 1) + ": " + startDate);
			}
		}

		//答案
		/*
		for (int i = 0; i < 5; i++) {
			LocalDate nextDate = startDate.plus(2 * i, ChronoUnit.WEEKS);
			System.out.println("日付 " + (i + 1) + ": " + nextDate);
		}
		*/
	}
}

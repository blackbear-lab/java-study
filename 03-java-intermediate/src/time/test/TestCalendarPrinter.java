package time.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class TestCalendarPrinter {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("年度を入力してください。：");
		int year = scanner.nextInt();
		System.out.print("月を入力してください。：");
		int month = scanner.nextInt();

		PrintCalendar(year, month);
	}

	private static void PrintCalendar(int year, int month) {
		LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
		LocalDate fistDayOfNextMonth = firstDayOfMonth.plusMonths(1);

		//月曜日=1(1%7=1) ．．．日曜日7(7%7=0)
		int offsetWeekDays = firstDayOfMonth.getDayOfWeek().getValue() % 7;

		System.out.println("Su Mo Tu We Th Fr Sa ");
		for (int i = 0; i < offsetWeekDays; i++) {
			System.out.print("   ");
		}
		LocalDate dayIterator = firstDayOfMonth;
		while (dayIterator.isBefore(fistDayOfNextMonth)) {
			System.out.printf("%2d ", dayIterator.getDayOfMonth());
			dayIterator = dayIterator.plusDays(1);
			if (dayIterator.getDayOfWeek() == DayOfWeek.SATURDAY) {
				System.out.println();
			}
		}
	}
}

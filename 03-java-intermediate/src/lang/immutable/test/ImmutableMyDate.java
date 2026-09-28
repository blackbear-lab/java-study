package lang.immutable.test;

public class ImmutableMyDate {

	private final int year;
	private final int month;
	private final int day;

	public ImmutableMyDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}


	public ImmutableMyDate withYear(int changeYear) {
		return new ImmutableMyDate(changeYear, month, day);
	}

	public ImmutableMyDate withMonth(int changeMonth) {
		return new ImmutableMyDate(year, changeMonth, day);
	}

	public ImmutableMyDate withDay(int changeDay) {
		return new ImmutableMyDate(year, month, changeDay);
	}

	@Override
	public String toString() {
		return year + "-" + month + "-" + day;
	}
}

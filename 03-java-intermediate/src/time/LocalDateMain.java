package time;

import java.time.LocalDate;

public class LocalDateMain {
	public static void main(String[] args) {
		LocalDate nowDate = LocalDate.now();
		LocalDate ofDate = LocalDate.of(2013, 11, 21);
		System.out.println("nowDate = " + nowDate);
		System.out.println("ofDate = " + ofDate);

		//計算(不変)
		ofDate = ofDate.plusDays(10);
		System.out.println("ofDate = " + ofDate);
	}

}

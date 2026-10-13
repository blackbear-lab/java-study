package time;

import java.time.LocalTime;

public class LocalTimeMain {

	public static void main(String[] args) {
		LocalTime nowTime = LocalTime.now();
		LocalTime ofTime = LocalTime.of(9, 10, 30);

		System.out.println("現在時刻 = " + nowTime);
		System.out.println("指定時刻 = " + ofTime);

		//計算(不変)
		LocalTime ofTimePlus = ofTime.plusSeconds(30);
		System.out.println("ofTimePlus = " + ofTimePlus);

	}
}

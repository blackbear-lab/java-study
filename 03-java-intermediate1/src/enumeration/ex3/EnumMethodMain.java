package enumeration.ex3;

import java.util.Arrays;

public class EnumMethodMain {

	public static void main(String[] args) {

		//すべてのENUM戻り
		Grade[] values = Grade.values();
		System.out.println("(values) = " + Arrays.toString(values));
		for (Grade value : values) {
			System.out.println("name = " + value.name() + ", ordinal = " + value.ordinal());
		}

		//String -> ENUM 変換
		String input = "GOLD";
		Grade gold = Grade.valueOf(input);
		System.out.println("gold = " + gold);
	}

}

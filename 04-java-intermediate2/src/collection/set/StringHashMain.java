package collection.set;

public class StringHashMain {

	static final int CAPACITY = 10;

	public static void main(String[] args) {
		//char
		char charA = 'A';
		char charB = 'B';
		System.out.println("charA = " + (int)charA);
		System.out.println("charB = " + (int)charB);

		// hashCode
		System.out.println();
		System.out.println("hasCode('A') = " + hashCode("A"));
		System.out.println("hasCode('A') = " + hashCode("B"));
		System.out.println("hasCode('A') = " + hashCode("C"));


		//hashIndex
		System.out.println("hashIndex(A) = " + hashIndex(hashCode("A")));
		System.out.println("hashIndex(B) = " + hashIndex(hashCode("B")));
		System.out.println("hashIndex(AB) = " + hashIndex(hashCode("AB")));
	}

	private static int hashIndex(int value) {
		return value % CAPACITY;
	}

	private static int hashCode(String str) {
		char[] charArray = str.toCharArray();
		int sum = 0;
		for (char c : charArray) {
			sum = sum + (int) c;
		}
		return sum;
	}
}

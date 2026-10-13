package nested.test;

public class OuterClass2Main {
	public static void main(String[] args) {
		OuterClass2 outerClass = new OuterClass2();
		OuterClass2.InnerClass innerClass = outerClass.new InnerClass();
		innerClass.hello();
	}

}

package nested.test;

public class OuterClass1Main {
	public static void main(String[] args) {
		OuterClass outerClass = new OuterClass();
		OuterClass.NestedClass nestedClass = new OuterClass.NestedClass();
		nestedClass.hello();
	}

}

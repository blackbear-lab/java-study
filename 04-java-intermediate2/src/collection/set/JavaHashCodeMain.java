package collection.set;

import collection.set.member.Member;

public class JavaHashCodeMain {
	// Objectの基本hashCodeはオブジェクトの参照値を元に生成
	public static void main(String[] args) {
		Object obj2 = new Object();
		Object obj1 = new Object();

		System.out.println("obj1.hashCode() = " + obj1.hashCode());
		System.out.println("obj2.hashCode() = " + obj2.hashCode());

		//各クラスごとにhashCodeを既にオーバーライドしている。
		Integer i = 10;
		String strA = "A";
		String strAB = "AB";

		System.out.println("10.hashCode() = " + i.hashCode());
		System.out.println("'A'.hashCode() = " + strA.hashCode());
		System.out.println("'AB'.hashCode() = " + strAB.hashCode());

		//hashCodeはマイナスの値が存在する。
		System.out.println("-1.hashCode() = " + Integer.valueOf(-1).hashCode());

		//二つの変数は同じ値を持つか？、インスタンスは違うが、equalsは同じだ
		Member member1 = new Member("idA");
		Member member2 = new Member("idA");

		//equals、hashCodeをオーバーライドしてない場合とした場合を比較
		System.out.println("(member1 == member2) = " + (member1 == member2));
		System.out.println("(member1 equals member2) = " + (member1.equals(member2)));

		System.out.println("member1.hashCode() = " + member1.hashCode());
		System.out.println("member2.hashCode() = " + member2.hashCode());
	}



}

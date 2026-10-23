package collection.map;

import java.util.HashMap;

public class MapMain2 {
	public static void main(String[] args) {
		HashMap<String, Integer> studentMap = new HashMap<>();

		//学生成績データ追加
		studentMap.put("studentA", 90);
		System.out.println(studentMap);

		//同じキーにデータ入力時、既存の値を捨てて新しい値を入れる
		studentMap.put("studentA", 100);
		System.out.println(studentMap);


		boolean containsKey = studentMap.containsKey("studentA");
		System.out.println("containsKey = " + containsKey);

		//キーで削除するとキーのデータも削除される
		studentMap.remove("studentA");
		System.out.println("studentMap = " + studentMap);
	}
}

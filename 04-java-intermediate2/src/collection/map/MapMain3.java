package collection.map;

import java.util.HashMap;

public class MapMain3 {
	public static void main(String[] args) {
		HashMap<String, Integer> studentMap = new HashMap<>();

		//学生成績データ追加
		studentMap.put("studentA", 50);
		System.out.println(studentMap);

		//学生が存在しない場合のみ追加
		if (!studentMap.containsKey("studentA")) {
			studentMap.put("studentA", 100);
		}

		//学生が存在しない場合のみ追加2
		studentMap.putIfAbsent("studentA", 100);
		studentMap.putIfAbsent("studentB", 100);
		System.out.println(studentMap);
	}
}

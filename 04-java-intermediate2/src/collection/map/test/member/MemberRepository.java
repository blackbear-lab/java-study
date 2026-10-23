package collection.map.test.member;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemberRepository {
	private Map<String, Member> memberMap = new HashMap<>();

	public void save(Member member) {
		// コード作成
		memberMap.put(member.getId(), member);
	}

	public void remove(String id) {
		// コード作成
		memberMap.remove(id);
	}

	public Member findById(String id) {
		// コード作成
		return memberMap.get(id);
	}

	public Member findByName(String name) {
		// コード作成
		for (Member member : memberMap.values()) {
			if (member.getName().equals(name)) {
				return member;
			}
		}
		return null;
	}

}

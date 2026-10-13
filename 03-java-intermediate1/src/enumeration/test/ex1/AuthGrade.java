package enumeration.test.ex1;

public enum AuthGrade {
	GUEST(1, "客"), LOGIN(2, "ログイン会員"), ADMIN(3, "管理者");

	private final int level;
	private final String description;

	AuthGrade(int level, String description) {
		this.level = level;
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public String getDescription() {
		return description;
	}
}

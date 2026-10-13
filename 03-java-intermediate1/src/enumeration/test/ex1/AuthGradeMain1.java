package enumeration.test.ex1;

public class AuthGradeMain1 {
	public static void main(String[] args) {

		//コード作成
		AuthGrade guest = AuthGrade.GUEST;
		AuthGrade login = AuthGrade.LOGIN;
		AuthGrade admin = AuthGrade.ADMIN;

		System.out.println("grade=" + guest.name() + ", level=" + guest.getLevel() + ", 説明=" + guest.getDescription());
		System.out.println("grade=" + login.name() + ", level=" + login.getLevel() + ", 説明=" + login.getDescription());
		System.out.println("grade=" + admin.name() + ", level=" + admin.getLevel() + ", 説明=" + admin.getDescription());

		//解答
		System.out.println();
		AuthGrade[] values = AuthGrade.values();
		for (AuthGrade value : values) {
			System.out.println("grade=" + value.name() + ", level=" + value.getLevel() + ", 説明=" + value.getDescription());
		}
	}
}

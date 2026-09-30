package lang.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassMetaMain {

	public static void main(String[] args) throws Exception {
		//	Class照会
		Class clazz = String.class;	// 1.クラスで照会
		//Class clazz1 = new String().getClass();	//2.インスタンスで照会
		//Class clazz2 = Class.forName("java.lang.String");	// 3.文字列で照会

		//すべてのフィールド出力
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println("field = " + field.getType() + " " + field.getName());
		}

		// すべてのメソッド出力
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println("method = " + method);
		}

		// 上位クラス情報出力
		System.out.println("Superclass: " + clazz.getSuperclass().getName());

		// インターフェース情報出力
		Class[] interfaces = clazz.getInterfaces();
		for (Class i : interfaces) {
			System.out.println("Interface = " + i.getName());
		}
	}
}

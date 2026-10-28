package thread.start;

public class BadThreadMain {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+ ": main() start");

		HelloThread helloThread = new HelloThread();
		System.out.println(Thread.currentThread().getName() + ":start 呼出前");
		helloThread.run();	//run直接実行
		System.out.println(Thread.currentThread().getName()+":start 呼出後");

		System.out.println(Thread.currentThread().getName()+ ": main() end");
	}
}

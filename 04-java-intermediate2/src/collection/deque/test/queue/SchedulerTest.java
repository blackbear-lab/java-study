package collection.deque.test.queue;

public class SchedulerTest {
	public static void main(String[] args) {
		//昼には作業を保存
		TaskScheduler scheduler = new TaskScheduler();
		scheduler.addTask(new CompressionTask());
		scheduler.addTask(new BackupTask());
		scheduler.addTask(new CleanTask());

		//夜中に実行
		System.out.println("作業始め");
		run(scheduler);
		System.out.println("作業完了");
	}

	private static void run(TaskScheduler scheduler) {
		while (scheduler.getRemainingTasks() > 0) {
			scheduler.processNextTask();
		}
	}
}

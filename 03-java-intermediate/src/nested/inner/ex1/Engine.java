package nested.inner.ex1;

//carのみ使用
public class Engine {

	private Car car;

	public Engine(Car car) {
		this.car = car;
	}

	public void start() {
		System.out.println("充電レベル確認： " + car.getChargeLevel());
		System.out.println(car.getModel() + "のエンジンを起動します。");
	}
}

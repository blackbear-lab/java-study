package nested.inner.ex1;

public class Car {
	private String model;
	private int chargeLevel;
	private Engine engine;

	public Car(String model, int chargeLevel) {
		this.model = model;
		this.chargeLevel = chargeLevel;
		this.engine = new Engine(this);
	}

	//Engineのみ使用するメソッド
	public String getModel() {
		return model;
	}

	//Egineのみ使用するメソッド
	public int getChargeLevel() {
		return chargeLevel;
	}

	public void start() {
		engine.start();
		System.out.println(model + " 起動完了");
	}
}

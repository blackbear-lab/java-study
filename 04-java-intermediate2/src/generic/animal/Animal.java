package generic.animal;

public class Animal {

	private String name;
	private int size;

	public Animal(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void sound() {
		System.out.println("動物の鳴き声");
	}

	@Override
	public String toString() {
		return "Animal{" +
				"name='" + name + '\'' +
				", size=" + size +
				'}';
	}
}

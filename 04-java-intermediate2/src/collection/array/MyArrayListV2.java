package collection.array;

import java.util.Arrays;

public class MyArrayListV2 {

	private static final int DEFAULT_CAPACITY = 5;

	private Object[] elementData;
	private int size = 0;

	public MyArrayListV2() {
		elementData = new Object[DEFAULT_CAPACITY];
	}

	public MyArrayListV2(int initialCapacity) {
		elementData = new Object[initialCapacity];
	}

	public int size() {
		return size;
	}

	public void add(Object e) {
		//コード追加
		if (size == elementData.length) {
			grow();
		}
		elementData[size] = e;
		size++;
	}

	//コード追加
	private void grow() {
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity * 2;

		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	public Object get(int index) {
		return elementData[index];
	}

	public Object set(int index, Object element) {
		Object oldValue = get(index);
		elementData[index] = element;
		return oldValue;
	}

	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
			if (o.equals(elementData[i])) {
				return i;
			}
		}
		return -1;
	}

	public String toString() {
		//[1, 2, 3, null, null] // size=3
		//[1, 2, 3] // size=3
		return Arrays.toString(Arrays.copyOf(elementData, size)) +
				" size=" + size + ", capacity=" + elementData.length;
	}

}

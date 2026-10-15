package collection.array;

import java.util.Arrays;

public class MyArrayListV4<E> {

	private static final int DEFAULT_CAPACITY = 5;

	private Object[] elementData;
	private int size = 0;

	public MyArrayListV4() {
		elementData = new Object[DEFAULT_CAPACITY];
	}

	public MyArrayListV4(int initialCapacity) {
		elementData = new Object[initialCapacity];
	}

	public int size() {
		return size;
	}

	public void add(Object e) {
		if (size == elementData.length) {
			grow();
		}
		elementData[size] = e;
		size++;
	}

	//コード追加
	public void add(int index, E e) {
		if (size == elementData.length) {
			grow();
		}
		//データ移動
		shiftRightFrom(index);
		elementData[index] = e;
		size++;
	}

	private void shiftRightFrom(int index) {
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
	}

	//コード追加
	public E remove(int index) {
		E oldValue = get(index);
		//データ移動
		shiftLeftFrom(index);
		size--;
		elementData[size] = null;
		return oldValue;
	}

	private void shiftLeftFrom(int index) {
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
	}

	private void grow() {
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity * 2;

		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		return (E) elementData[index];
	}

	public E set(int index, E element) {
		E oldValue = get(index);
		elementData[index] = element;
		return oldValue;
	}

	public int indexOf(E o) {
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

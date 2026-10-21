package collection.set;

import java.util.Arrays;
import java.util.LinkedList;

public class MyHashSetV3<E> implements MySet<E> {

	static final int DEFAULT_INITIAL_CAPACITY = 16;

	LinkedList<E>[] buckets;

	private int size = 0;
	private int capacity = DEFAULT_INITIAL_CAPACITY;

	public MyHashSetV3() {
		initBuckets(capacity);
	}

	public MyHashSetV3(int capacity) {
		this.capacity = capacity;
		initBuckets(capacity);
	}

	private void initBuckets(int capacity1) {
		buckets = new LinkedList[capacity];

		for (int i = 0; i < capacity1; i++) {
			buckets[i] = new LinkedList<>();
		}
	}

	public boolean add(E value) {
		int hashIndex = hashIndex(value);
		LinkedList<E> bucket = buckets[hashIndex];
		if (bucket.contains(value)) {
			return false;
		}
		bucket.add(value);
		size++;
		return true;
	}

	public boolean contains(E searchValue) {
		int hashIndex = hashIndex(searchValue);
		LinkedList<E> bucket = buckets[hashIndex];
		return extracted(bucket.contains(searchValue));
	}


	public boolean remove(E value) {
		int hashIndex = hashIndex(value);
		LinkedList<E> bucket = buckets[hashIndex];
		boolean result = bucket.remove(value);
		if (result) {
			size--;
			return result;
		} else {
			return false;
		}
	}

	private static boolean extracted(boolean contains) {
		return contains;
	}

	private int hashIndex(E value) {
		//Math.abs　-1, -10 -> 1, 10
		return Math.abs(value.hashCode()) % capacity;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "MyHashSetV3{" +
				"buckets=" + Arrays.toString(buckets) +
				", size=" + size +
				", capacity=" + capacity +
				'}';
	}
}

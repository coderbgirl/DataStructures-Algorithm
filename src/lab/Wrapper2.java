package lab;

import java.util.Iterator;

public class Wrapper2<T> implements Iterable<T> {
	private int maxSize = 5;

	private T[] array = (T[]) new Object[maxSize];
	private int size = 0;
	
	public T get(int index) {
		if (index < 0 || index > length()) {
			System.out.println();
		}
		return array[index];
	}

	public T set(int index, T value) {
		if (index < 0 || index > length()) {
			System.out.println("Index out of bounds.");
		}
		array[index] = value;
		return array[index];
	}

	public int length() {
		return size;
	}

	public T push(T value) {
		//Resizing the array if needed automatically and then pushing the value
		if (size >= maxSize) {
			T[] array2 = (T[]) new Object[maxSize * 2];
			for (int i = 0; i < maxSize; i++) {
				array2[i] = array[i];
			}
			array = array2;
			maxSize = maxSize * 2;
		}
		array[size] = value;
		size++;
		return value;
	}

	public T pop() {
		if (size == 0) {
			return null;
		}
		T remove = array[size - 1];
		size--;
		return remove;
	}

	public boolean insert(int index, T value) {
		if (index < 0 || index > length() || size >= 10000) {
			System.out.println("Index out of bounds.");

		} else {
			for (int i = length() - 1; i >= index; i--) {
				array[i + 1] = array[i];
			}
			array[index] = value;
			size++;
		}
		return true;

	}

	private class Iterator2 implements Iterator<T> {
		int currIndex = 0;

		@Override
		public boolean hasNext() {
			if(currIndex < size){
				return true;
			}
			return false;
		}

		@Override
		public T next() {
			if (currIndex >= size) {
				// throw java.lang.IndexOutOfBoundsException;
				return null;
			}
			return array[currIndex++];
		}

		@Override
		public void remove() {
			for (int i = currIndex; i < size; i++) {
				array[i - 1] = array[i];
			}
			size--;
			currIndex--;
		}
	}

	public Iterator<T> iterator() {
		return new Iterator2();
	}
}

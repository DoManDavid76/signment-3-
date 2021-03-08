/**
 * @author David Do
 * 
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class BasicDoubleLinkedList<T> implements Iterable<T> {
	
	protected int size;
	protected Node<T> head;
	protected Node<T> tail;

	protected class Node<T> {
		protected T data;
		protected Node<T> next;
		protected Node<T> prev;

		protected Node(T data) {
			this.data = data;

		}
	}

	public BasicDoubleLinkedList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	
	
	public int getSize() {
		return size;
	}
	
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node<T> newNode = new Node<T>(data);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;

		return this;
	}
	
	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node<T> newNode = new Node<T>(data);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
		}
		size++;

		return this;
	}
	
	public T getFirst() {
		return head==null? null: head.data;
	}
	
	public T getLast(){
		return tail!=null? tail.data : null;
	}
	
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		Node<T> current = head;
		while (current != null) {
			if (comparator.compare(targetData, current.data) == 0) {
				if (current == head) {
					head = head.next;
					size--;
					break;
				} else if (current == tail) {
					tail = tail.prev;
					tail.next = null;
					size--;
					break;
				} else {
					current.prev.next = current.next;
					current.next.prev = current.prev;
					size--;
					break;
				}

			}
			current = current.next;

		}

		return this;
	}
	
	public T retrieveFirstElement() {
		if (isEmpty()) {
			return null;
		}
		T firstData = head.data;
		head = head.next;
		head.prev = null;
		size--;
		return firstData;
	}
	
	public T retrieveLastElement() {
		if (isEmpty()) {
			return null;
		}
		T lastData = tail.data;
		tail = tail.prev;
		tail.next = null;
		size--;
		return lastData;
	}

	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>(getSize());

		Node<T> current = head;

		while (current != null) {
			list.add(current.data);
			current = current.next;
		}

		return list;
	}
	
	public boolean isEmpty() {
		return size == 0; 
	}
	
	
	@Override
	public ListIterator<T> iterator()  throws UnsupportedOperationException,NoSuchElementException {
		return new ListIterator<T>() {

			Node<T> current = head;
			Node<T> lastMove = null;

			@Override
			public boolean hasNext() {

				return current != null;
			}

			@Override
			public T next() throws NoSuchElementException {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				T currentData = current.data;
				lastMove = current;
				current = current.next;
				return currentData;
			}

			public boolean hasPrevious() {
				return lastMove != null;
			}

			public T previous() throws NoSuchElementException {
				if (!hasPrevious()) {
					throw new NoSuchElementException();
				}
				T previousData = lastMove.data;
				current = lastMove;
				lastMove = lastMove.prev;
				return previousData;
			}

			@Override
			public void add(T e) {
				throw new UnsupportedOperationException();

			}

			@Override
			public int nextIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int previousIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();

			}

			@Override
			public void set(T e) {
				throw new UnsupportedOperationException();

			}

		};
	}
	
}

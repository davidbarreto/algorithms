package br.com.dbarreto.datastructure.tree.heap.impl;

import br.com.dbarreto.datastructure.tree.heap.Heap;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class BinaryHeap<E> implements Heap<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private final Object[] heapArray;
    private final int capacity;
    private final Comparator<? super E> comparator;

    private int size;

    public BinaryHeap(Comparator<? super E> comparator) {
        this(DEFAULT_CAPACITY, comparator, new Object[DEFAULT_CAPACITY]);
    }

    public BinaryHeap(int capacity, Comparator<? super E> comparator) {
        this(validateCapacity(capacity), comparator, new Object[capacity]);
    }

    private BinaryHeap(int capacity, Comparator<? super E> comparator, Object[] heapArray) {
        this.heapArray = heapArray;
        this.capacity = capacity;
        this.comparator = Objects.requireNonNull(comparator);
        this.size = 0;
    }

    public static <E> BinaryHeap<E> from(Collection<E> elements, Comparator<? super E> comparator) {
        var array = elements.toArray();
        BinaryHeap<E> heap = new BinaryHeap<>(array.length, comparator, array);
        heap.size = array.length;
        heap.heapify();

        return heap;
    }

    private static int validateCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        return capacity;
    }

    @Override
    public void insert(E value) {

        if (size == capacity) {
            throw new IllegalStateException("Heap is full. Capacity: " + capacity);
        }

        heapArray[size] = value;
        siftUp(size);
        size++;
    }

    @Override
    public E peek() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return elementAt(0);
    }

    @Override
    public E extract() {

        if (size <= 0) {
            throw new IllegalStateException("Heap is empty");
        }

        if (size == 1) {
            size--;
            return elementAt(0);
        }

        E root = elementAt(0);

        heapArray[0] = heapArray[size - 1];
        size--;

        heapArray[size] = null;

        if (!isEmpty()) {
            siftDown(0);
        }

        return root;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
    }



    @SuppressWarnings("unchecked")
    private E elementAt(int i) {
        return (E) heapArray[i];
    }

    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void swap(int i, int j) {
        Object temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void siftUp(int i) {
        while (i > 0) {
            int p = parent(i);
            if (less(i, p)) {
                break;
            }
            swap(i, p);
            i = p;
        }
    }

    private void siftDown(int i) {

        while (true) {
            int left = left(i);
            int right = right(i);
            int smallest = i;

            if (left < size && less(left, smallest)) {
                smallest = left;
            }
            if (right < size && less(right, smallest)) {
                smallest = right;
            }
            if (smallest == i) {
                break;
            }

            swap(i, smallest);
            i = smallest;
        }
    }

    private boolean less(int i, int j) {
        return comparator.compare(elementAt(i), elementAt(j)) < 0;
    }
}

package br.com.dbarreto.algorithm.sort;

import br.com.dbarreto.datastructure.tree.heap.impl.BinaryHeap;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {

    private Sorting() {}

    public static <E> void bubbleSort(List<E> list, Comparator<? super E> comparator) {

        boolean hasSwapped;
        do {
            hasSwapped = false;
            for (int i = 0; i < list.size() - 1; i++) {
                if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                    Collections.swap(list, i, i + 1);
                    hasSwapped = true;
                }
            }
        } while(hasSwapped);
    }

    public static <E> void selectionSort(List<E> list, Comparator<? super E> comparator) {

        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            Collections.swap(list, i, minIndex);
        }
    }

    public static <E> void insertionSort(List<E> list, Comparator<? super E> comparator) {

        for (int i = 1; i < list.size(); i++) {
            int insertIndex = i;
            E currentValue = list.get(i);
            for (int j = i-1; j > 0; j--) {
                if (comparator.compare(list.get(j), currentValue) > 0) {
                    list.set(j + 1, list.get(j));
                    insertIndex = j;
                } else {
                    break;
                }
            }
            list.set(insertIndex, currentValue);
        }
    }

    public static <E> void heapSort(List<E> list, Comparator<? super E> comparator) {
        var heap = BinaryHeap.from(list, comparator);
        list.replaceAll(ignored -> heap.extract());
    }
}

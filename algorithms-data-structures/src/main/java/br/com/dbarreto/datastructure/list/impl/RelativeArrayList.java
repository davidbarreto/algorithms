package br.com.dbarreto.datastructure.list.impl;

import java.util.ArrayList;

public class RelativeArrayList<E> extends ArrayList<E> {

    @Override
    public E get(int index) {
        return super.get(normalizeIndex(index));
    }

    @Override
    public E set(int index, E element) {
        return super.set(normalizeIndex(index), element);
    }

    @Override
    public void add(int index, E element) {
        super.add(normalizeIndex(index), element);
    }

    @Override
    public E remove(int index) {
        return super.remove(normalizeIndex(index));
    }

    private int normalizeIndex(int index) {
        if (index < 0) {
            index = size() + index;
        }
        return index;
    }
}

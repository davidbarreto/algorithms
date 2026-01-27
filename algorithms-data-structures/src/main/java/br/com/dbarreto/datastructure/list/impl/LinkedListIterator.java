package br.com.dbarreto.datastructure.list.impl;

import br.com.dbarreto.datastructure.node.list.SinglyLinkedNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedListIterator<E> implements Iterator<E>{

    private SinglyLinkedNode<E> current;

    LinkedListIterator(SinglyLinkedNode<E> head) {
        this.current = head;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E value = current.value();
        current = current.next();
        return value;
    }
}

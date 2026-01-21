package br.com.dbarreto.datastructure.list.impl;

import br.com.dbarreto.datastructure.list.Deque;
import br.com.dbarreto.datastructure.node.list.impl.DoublyListNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements Deque<E> {

    private DoublyListNode<E> head;
    private DoublyListNode<E> tail;

    @Override
    public void addFirst(E value) {
        addFirst(new DoublyListNode<>(value));
    }

    public void addFirst(DoublyListNode<E> node) {
        if (head == null) {
            node.setPrevious(null);
            node.setNext(null);
            head = node;
            tail = node;
        } else {
            node.setPrevious(null);
            node.setNext(head);
            head.setPrevious(node);
            head = node;
        }
    }

    @Override
    public void addLast(E value) {
        addLast(new DoublyListNode<>(value));
    }

    public void addLast(DoublyListNode<E> node) {
        if (head == null) {
            node.setPrevious(null);
            node.setNext(null);
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
            node.setNext(null);
            tail = node;
        }
    }

    @Override
    public E removeFirst() {
        return remove(head);
    }

    @Override
    public E removeLast() {
        return remove(tail);
    }

    public E remove(DoublyListNode<E> node) {
        if (node == null) {
            return null;
        }

        if (node == head) {
            head = node.next();
            if (head != null) {
                head.setPrevious(null);
            } else {
                tail = null;
            }
            return node.value();
        }

        if (node == tail) {
            tail = node.previous();
            if (tail != null) {
                tail.setNext(null);
            } else {
                head = null;
            }
            return node.value();
        }

        if (node.previous() != null) {
            node.previous().setNext(node.next());
        }

        if (node.next() != null) {
            node.next().setPrevious(node.previous());
        }

        return node.value();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        int size = 0;
        var current = head;
        while (current != null) {
            size++;
            current = current.next();
        }
        return size;
    }

    public void moveToHead(DoublyListNode<E> node) {
        remove(node);
        addFirst(node);
    }

    public void moveToTail(DoublyListNode<E> node) {
        remove(node);
        addLast(node);
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private DoublyListNode<E> current = head;

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
        };
    }
}

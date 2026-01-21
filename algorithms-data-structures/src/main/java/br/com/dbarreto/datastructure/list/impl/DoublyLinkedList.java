package br.com.dbarreto.datastructure.list.impl;

import br.com.dbarreto.datastructure.node.list.impl.DoublyListNode;

public class DoublyLinkedList<E> {

    private DoublyListNode<E> head;
    private DoublyListNode<E> tail;

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
            head = node;
        }
    }

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

    public E removeFirst() {
        return remove(head);
    }

    public E removeLast() {
        return remove(tail);
    }

    public E remove(DoublyListNode<E> node) {

        if (node == head) {
            head = node.next();
            return node.value();
        }

        if (node == tail) {
            tail = node.previous();
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

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int size = 0;
        DoublyListNode<E> current = head;
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

    public void clear() {
        head = null;
        tail = null;
    }
}

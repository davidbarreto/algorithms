package br.com.dbarreto.datastructure.list.impl;

import br.com.dbarreto.datastructure.list.Queue;
import br.com.dbarreto.datastructure.node.list.SinglyLinkedNode;

import java.util.Iterator;

public class SinglyLinkedList<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public void add(E value) {
        addAndReturn(value);
    }

    public void add(SinglyLinkedNode<E> node) {
        if (node instanceof Node<E> n) {
            add(n);
        } else {
            throw new IllegalArgumentException("Node type not supported");
        }
    }

    private void add(Node<E> node) {

        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public SinglyLinkedNode<E> addAndReturn(E value) {
        var node = new Node<>(value);
        add(node);
        return node;
    }

    @Override
    public E remove() {
        if (head == null) {
            return null;
        }

        E value = head.value;
        if (head == tail) {
            head = null;
        } else {
            head = head.next;
        }

        size--;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(head);
    }

    private static class Node<E> implements SinglyLinkedNode<E> {

        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public E value() {
            return value;
        }

        @Override
        public void setValue(E value) {
            this.value = value;
        }

        @Override
        public SinglyLinkedNode<E> next() {
            return next;
        }
    }
}

package br.com.dbarreto.datastructure.list.impl;

import br.com.dbarreto.datastructure.list.Deque;
import br.com.dbarreto.datastructure.node.list.DoublyLinkedNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements Deque<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    @Override
    public void addFirst(E value) {
        addFirst(new Node<>(value));
    }

    public void addFirst(DoublyLinkedNode<E> node) {
        if (node instanceof Node<E> n) {
            addFirst(n);
        } else {
            throw new IllegalArgumentException("Node type not supported");
        }
    }

    private void addFirst(Node<E> node) {
        if (head == null) {
            node.previous = null;
            head = node;
            tail = node;
        } else {
            node.previous = null;
            node.next = head;
            head.previous = node;
            head = node;
        }
        size++;
    }

    @Override
    public void addLast(E value) {
        addLast(new Node<>(value));
    }

    public DoublyLinkedNode<E> addToLast(E value) {
        Node<E> node = new Node<>(value);
        addLast(node);
        return node;
    }

    public void addLast(DoublyLinkedNode<E> node) {
        if (node instanceof Node<E> n) {
            addLast(n);
        } else {
            throw new IllegalArgumentException("Node type not supported");
        }
    }

    private void addLast(Node<E> node) {
        if (head == null) {
            node.previous = null;
            node.next = null;
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            node.next = null;
            tail = node;
        }
        size++;
    }

    @Override
    public E removeFirst() {
        return remove(head);
    }

    @Override
    public E removeLast() {
        return remove(tail);
    }

    public E remove(DoublyLinkedNode<E> node) {
        if (node instanceof Node<E> n) {
            return remove(n);
        } else {
            throw new IllegalArgumentException("Node type not supported");
        }
    }

    private E remove(Node<E> node) {

        if (node == null) {
            return null;
        }

        if (node == head) {
            head = node.next;
            if (head != null) {
                head.previous = null;
            } else {
                tail = null;
            }
            size--;
            return node.value();
        }

        if (node == tail) {
            tail = node.previous;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            size--;
            return node.value();
        }

        boolean unlinked = false;
        if (node.previous() != null) {
            node.previous.next = node.next;
            unlinked = true;
        }

        if (node.next() != null) {
            node.next.previous = node.previous;
            unlinked = true;
        }

        if (unlinked) {
            size--;
        }

        return node.value();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    public void moveToHead(DoublyLinkedNode<E> node) {
        remove(node);
        addFirst(node);
    }

    public void moveToTail(DoublyLinkedNode<E> node) {
        remove(node);
        addLast(node);
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    private static class Node<E> implements DoublyLinkedNode<E> {

        private E value;
        private Node<E> next;
        private Node<E> previous;

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
        public DoublyLinkedNode<E> next() {
            return next;
        }

        @Override
        public DoublyLinkedNode<E> previous() {
            return previous;
        }
    }
}

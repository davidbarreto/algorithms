package br.com.dbarreto.datastructure.node.list.impl;

public class DoublyListNode<E> {

    private E value;
    private DoublyListNode<E> previous;
    private DoublyListNode<E> next;

    public DoublyListNode(E value) {
        this.value = value;
    }

    public DoublyListNode<E> next() {
        return this.next;
    }

    public void setNext(DoublyListNode<E> next) {
        this.next = next;
    }

    public DoublyListNode<E> previous() {
        return this.previous;
    }

    public void setPrevious(DoublyListNode<E> previous) {
        this.previous = previous;
    }

    public E value() {
        return this.value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}

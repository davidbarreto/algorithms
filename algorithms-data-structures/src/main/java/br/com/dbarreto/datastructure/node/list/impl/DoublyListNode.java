package br.com.dbarreto.datastructure.node.list.impl;

import br.com.dbarreto.datastructure.node.list.DoublyLinkedNode;

public class DoublyListNode<E> implements DoublyLinkedNode<E, DoublyListNode<E>> {

    private E value;
    private DoublyListNode<E> previous;
    private DoublyListNode<E> next;

    public DoublyListNode(E value) {
        this.value = value;
    }

    @Override
    public DoublyListNode<E> next() {
        return this.next;
    }

    @Override
    public void setNext(DoublyListNode<E> next) {
        this.next = next;
    }

    @Override
    public DoublyListNode<E> previous() {
        return this.previous;
    }

    @Override
    public void setPrevious(DoublyListNode<E> previous) {
        this.previous = previous;
    }

    @Override
    public E value() {
        return this.value;
    }

    @Override
    public void setValue(E value) {
        this.value = value;
    }
}

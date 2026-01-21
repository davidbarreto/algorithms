package br.com.dbarreto.datastructure.node.list.impl;

import br.com.dbarreto.datastructure.node.list.SinglyLinkedListNode;

public class SinglyListNode<E> implements SinglyLinkedListNode<E, SinglyListNode<E>> {

    private E value;
    private SinglyListNode<E> next;

    public SinglyListNode(E value) {
        this.value = value;
    }

    @Override
    public SinglyListNode<E> next() {
        return this.next;
    }

    @Override
    public void setNext(SinglyListNode<E> next) {
        this.next = next;
    }

    @Override
    public void setValue(E value) {
        this.value = value;
    }

    @Override
    public E value() {
        return this.value;
    }
}

package br.com.dbarreto.datastructure.node.list;

public interface DoublyLinkedNode<E> extends SinglyLinkedNode<E> {
    @Override
    DoublyLinkedNode<E> next();
    DoublyLinkedNode<E> previous();
}

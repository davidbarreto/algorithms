package br.com.dbarreto.datastructure.node.list;

public interface SinglyLinkedNode<E> {
    E value();
    void setValue(E value);
    SinglyLinkedNode<E> next();
}

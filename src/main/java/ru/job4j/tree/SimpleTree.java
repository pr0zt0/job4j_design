package ru.job4j.tree;

import org.checkerframework.checker.nullness.Opt;

import java.util.*;
import java.util.function.Predicate;


public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = true;
        Optional<Node<E>> par = findBy(parent);
        if (par.isEmpty() || findBy(child).isPresent()) {
            return false;
        }
        Node<E> val = new Node<>(child);
        par.get().children.add(val);
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Optional<Node> els = findByPredicate(el -> el.value.equals(value));
        if (els.isEmpty()) {
            return  rsl;
        } else {
            return Optional.of(els.get());
        }
    }

    public boolean isBinary() {
        return findByPredicate(val -> val.children.size() > 2).isEmpty();
    }

    private Optional<Node> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                return Optional.of(el);
            }
            data.addAll(el.children);
        }
        return Optional.empty();
    }

}
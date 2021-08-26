package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count / capacity >= LOAD_FACTOR) {
            expand();
        }

        int h = key.hashCode();
        int hash = hash(h);
        int index = indexFor(hash);

        if (table[index] == null) {
            enterEl(key, value, index);
            return true;
        } else {
            if (table[index].key.equals(key)) {
                table[index].value = value;
                return true;
            }
        }
        return false;
    }

    private void enterEl(K key, V value, int index) {
        MapEntry<K, V> el = new MapEntry<>(key, value);
        table[index] = el;
        count++;
        modCount++;
    }

    private int hash(int hashCode) {
        return hashCode % table.length;
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[oldTable.length * 2];
        count = 0;
        modCount = 0;
        for (MapEntry<K, V> el : oldTable) {
            if (el != null) {
                put(el.key, el.value);
            }
        }
    }

    @Override
    public V get(K key) {
        int h = key.hashCode();
        int hash = hash(h);
        int index = indexFor(hash);

        MapEntry<K, V> el = table[index];

        if (el != null && el.key.equals(key)) {
            return el.value;
        }

        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private MapEntry<K, V>[] curr = table;
            private int cursor = 0;
            private int exModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor < curr.length && nextNotNull();
            }

            private boolean nextNotNull() {
                boolean nextNull = false;
                for (int i = cursor; i < curr.length; i++) {
                    if (curr[i] != null) {
                        nextNull = true;
                        cursor = i;
                        break;
                    }
                }
                return nextNull;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (exModCount != modCount) {
                    throw new ConcurrentModificationException("mod array!");
                }
                return curr[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
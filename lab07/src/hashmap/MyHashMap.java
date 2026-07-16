package hashmap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private double loadFactor;
    private int capacity;

    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;


    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.size = 0;
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.buckets = (Collection<Node>[]) new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            this.buckets[i] = createBucket(); // 所有的初始化工作都在这里完成
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Collection<Node>[] oldBuckets = buckets;

        buckets = (Collection<Node>[]) new Collection[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            buckets[i] = createBucket();
        }

        for (Collection<Node> bucket : oldBuckets) {
            for (Node node : bucket) {
                int index = bucketIndex(node.key);
                buckets[index].add(node);
            }
        }

        capacity = newCapacity;
    }
    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new HashSet<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    private int bucketIndex(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    private Node findNode(K key) {
        int index = bucketIndex(key);
        for (Node node : buckets[index]) {
            if (key.equals(node.key)) {
                return node;
            }
        }

        return null;
    }

    @Override
    public void put(K key, V value) {
        Node node = findNode(key);
        if (node != null) {
            node.value =  value;
            return;
        }

        if ((size + 1.0) / buckets.length > loadFactor) {
            resize(buckets.length * 2);
        }

        int index = bucketIndex(key);
        buckets[index].add(new Node(key, value));
        size++;
    }

    @Override
    public V get(K key) {
        Node node = findNode(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Override
    public boolean containsKey(K key) {
        Node node = findNode(key);
        return node != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (Collection<Node> bucket : buckets) {
            bucket.clear();
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                keys.add(node.key);
            }
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        int index = bucketIndex(key);
        Iterator<Node> bucketIterator = buckets[index].iterator();
        while (bucketIterator.hasNext()) {
            Node node = bucketIterator.next();
            if (key.equals(node.key)) {
                V removedValue = node.value;
                bucketIterator.remove();
                size--;
                return removedValue;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}

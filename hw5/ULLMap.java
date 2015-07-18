import java.util.Set; /* java.util.Set needed only for challenge problem. */
import java.util.Iterator;

/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap<K, V> implements Map61B<K, V> { //FIX ME
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
      */
    private Entry front;

    @Override
    public V get(K key) { //FIX ME
    //FILL ME IN
        Entry pEntry = front;
        while (!pEntry.key.equals(key)) {
            if (pEntry == null) {
                throw new UnsupportedOperationException();
            }
            pEntry = pEntry.next;
        }
        return pEntry.val;
    }

    @Override
    public void put(K key, V val) { //FIX ME
    //FILL ME IN
        Entry entryToPut = new Entry(key, val, null);
        if (front == null) {
            front = entryToPut;
            return;
        }
        Entry pEntry = front;
        while (pEntry.next != null) {
            pEntry = pEntry.next;
        }
        pEntry.next = entryToPut;
    }

    @Override
    public boolean containsKey(K key) { //FIX ME
    //FILL ME IN
        Entry pEntry = front;
        while (pEntry != null) {
            if (pEntry.key.equals(key)) {
                return true;
            }
            pEntry = pEntry.next;
        }
        return false; //FIX ME
    }

    @Override
    public int size() {
        Entry pEntry = front;
        int count = 0;
        while (pEntry != null) {
            pEntry = pEntry.next;
            count++;
        }
        return count;
    }

    @Override
    public void clear() {
    //FILL ME IN
        front = null;
    }

    public ULLMap<V, K> invert() {
        ULLMap<V, K> um = new ULLMap<V, K>();
        um.front = um.new Entry(front.val, front.key, null);
        ULLMap<V, K>.Entry p = um.front;
        Entry pEntry = front;
        while (pEntry.next != null) {
            pEntry = pEntry.next;
            if (!um.containsKey(pEntry.val)) {
                p.next = um.new Entry(pEntry.val, pEntry.key, null);
                p = p.next;
            }
        }
        return um;
    }


    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(K k, V v, Entry n) { //FIX ME
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(K k) { //FIX ME
            //FILL ME IN (using equals, not ==)
            return null; //FIX ME
        }

        /** Stores the key of the key-value pair of this node in the list. */
        public K key; //FIX ME
        /** Stores the value of the key-value pair of this node in the list. */
        public V val; //FIX ME
        /** Stores the next Entry in the linked list. */
        public Entry next;
    
    }

    public Iterator<E> iterator() {
        return new ULLMapIter<E>(this);
    }

    public class ULLMapIter<E> implements Iterator<E> {
        private E data;
        private Entry iterp;

        public ULLMapIter(ULLMap um) {
            iterp = um.front;
            data = (E)iterp.key;
        }

        public boolean hasNext() {
            return iterp != null;
        }

        public E next() {
            if (iterp == null) {
                return null;
            }
            E item = (E)iterp.key;
            iterp = iterp.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* Methods below are all challenge problems. Will not be graded in any way. 
     * Autograder will not test these. */
    @Override
    public V remove(K key) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }


}
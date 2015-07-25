import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {

	private class Node {
		public Node leftChild = null;
		public Node rightChild = null;
		public K keyNode;
		public V valueNode;

		public Node(K _keyNode, V _valueNode) {
			keyNode = _keyNode;
			valueNode = _valueNode;
		}
	}

	private Node root;
	private int count;

	public BSTMap() {
		root = null;
		count = 0;
	}

	public void clear() {
		root = null;
	}

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
    	Node p = root;
    	while (p != null) {
    		if (key.compareTo(p.keyNode) < 0) {
    			p = p.leftChild;
    			continue;
    		} else if (key.compareTo(p.keyNode) > 0) {
    			p = p.rightChild;
    			continue;
    		} else {
    			return true;
    		}
    	}
    	return false;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
    public V get(K key) {
    	Node p = root;
    	while (p != null) {
    		if (key.compareTo(p.keyNode) < 0) {
    			p = p.leftChild;
    			continue;
    		} else if (key.compareTo(p.keyNode) > 0) {
    			p = p.rightChild;
    			continue;
    		} else {
    			return p.valueNode;
    		}
    	}
    	return null;
    }

   /* Returns the number of key-value mappings in this map. */
    public int size() {
    	count = 0;
    	visit(root);
    	return count;
    }

    private void visit(Node n) {
    	if (n == null) {
    		return;
    	}
    	count++;
    	visit(n.leftChild);
    	visit(n.rightChild);
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
    	if (root == null) {
    		root = new Node(key, value);
    		return;
    	}
    	Node p = root;
    	Node pSuper = null;
    	while (p != null) {
    		if (key.compareTo(p.keyNode) < 0) {
    			pSuper = p;
    			p = p.leftChild;
    		} else if (key.compareTo(p.keyNode) > 0) {
    			pSuper = p;
    			p = p.rightChild;
    		} else {
    			p.valueNode = value;
    			return;
    		}
    	}
    	if (key.compareTo(pSuper.keyNode) < 0) {
    		pSuper.leftChild = new Node(key, value);
    	} else {
    		pSuper.rightChild = new Node(key, value);
    	}
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for HW6. */
    public V remove(K key) {
    	return null;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for HW6a. */
    public V remove(K key, V value) {
    	return null;
    }

    /* Returns a Set view of the keys contained in this map. Not required for HW6. */
    public Set<K> keySet(){
    	return null;
    }
}
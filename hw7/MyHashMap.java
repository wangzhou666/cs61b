import java.util.Set;
import java.util.HashSet;

public class MyHashMap<K, V> implements Map61B<K, V> {

	private K[] keyArray;
	private V[] valueArray;
	private int[] hashArray;
	private int size;
	private float loadFactor;
	
	public MyHashMap() {
		this(10);
	}
	public MyHashMap(int _initialSize) {
		this(_initialSize, (float) 0.75);
	}
	public MyHashMap(int _initialSize, float _loadFactor) {
		size = _initialSize;
		loadFactor = _loadFactor;
		keyArray = (K[]) new Object[size];
		valueArray = (V[]) new Object[size];
		hashArray = new int[size];
	}

	public void clear() {
		keyArray = (K[]) new Object[size];
		valueArray = (V[]) new Object[size];
		hashArray = new int[size];
	}

	private int indexShouldBe(K key) {
		return key.hashCode() % size;
	}

	private int nextIndex(int i, int total) {
		if (i == total - 1) {
			return 0;
		}
		return i + 1;
	}

    /* Returns true if this map contains a mapping for the specified key. 
     * Should run on average constant time when called on a HashMap. 
     */
    public boolean containsKey(K key) {
    	int index = indexShouldBe(key);
    	int i = index;
    	do {
    		if (keyArray[i].equals(key)) {
    			return true;
    		}
    		i = nextIndex(i, size);
    	} while (i != index);
    	return false;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. Should run on average constant time
     * when called on a HashMap. 
     */
    public V get(K key) {
    	int index = indexShouldBe(key);
    	int i = index;
    	do {
    		if (keyArray[i].equals(key)) {
    			return valueArray[i];
    		}
    		i = nextIndex(i, size);
    	} while (i != index);
    	return null;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
    	int count = 0;
    	for (int i = 0; i < size; i++) {
    		if (keyArray[i] != null) {
    			count++;
    		}
    	}
    	return count;
    }

    /* Associates the specified value with the specified key in this map. 
     * Should run on average constant time when called on a HashMap. */
    public void put(K key, V value) {
    	if (containsKey(key)) {
    		return;
    	}
    	int index = indexShouldBe(key);
    	int i = index;
    	do {
    		if (keyArray[i] == null) {
    			keyArray[i] = key;
    			valueArray[i] = value;
    			hashArray[i] = key.hashCode();
    			break;
    		}
    		i = nextIndex(i, size);
    	} while (i != index);
    	if (size() >= size * loadFactor) {
    		rehash(2);
    	}
    }

    private void rehash(int rehashFactor) {
    	int tempSize = size * rehashFactor;
    	K[] tempKeyArray = (K[]) new Object[tempSize];
    	V[] tempValueArray = (V[]) new Object[tempSize];
    	int[] tempHashArray = new int[tempSize];
    	for (int i = 0; i < size; i++) {
    		if (keyArray[i] == null) {
    			continue;
    		}
    		int index = keyArray[i].hashCode() % tempSize;
    		int j = index;
    		do {
    			if (tempKeyArray[j] == null) {
    				tempKeyArray[j] = keyArray[i];
    				tempValueArray[j] = valueArray[i];
    				tempHashArray[j] = hashArray[i];
    				break;
    			}
    			j = nextIndex(j, tempSize);
    		} while(j != index);
    	}
    	size = tempSize;
    	keyArray = tempKeyArray;
    	valueArray = tempValueArray;
    	hashArray = tempHashArray;
    }		

    /* Removes the mapping for the specified key from this map if present. 
     * Should run on average constant time when called on a HashMap. */
    public V remove(K key) {
    	return remove(key, get(key));
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Should run on average constant time when called on 
     * a HashMap. */
    public V remove(K key, V value) {
    	int index = indexShouldBe(key);
    	int i = index;
    	do {
    		if (keyArray[i].equals(key)) {
    			if (valueArray[i].equals(value)) {
    				V valueToReturn = valueArray[i];
	    			keyArray[i] = null;
	    			valueArray[i] = null;
	    			return valueToReturn;
    			}
    			return null;
    		}
    		i = nextIndex(i, size);
    	} while (i != index);
    	return null;
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
    	Set<K> setKey = new HashSet<K>();
    	for (int i = 0; i < size; i++) {
    		if (keyArray[i] == null) {
    			continue;
    		}
    		setKey.add(keyArray[i]);
    	}
    	return setKey;
    }
}
package ngordnet;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Set;
import java.util.Collection;
import java.util.LinkedList;
import java.util.HashSet;

public class YearlyRecord<String, Integer> extends HashMap<String, Integer> {

	private TreeMap<Integer, Set<String>> mapCntWord;

	public YearlyRecord() {
		super();
		mapCntWord = new TreeMap<Integer, Set<String>>();
	}

	public YearlyRecord(HashMap<String, Integer> hmp) {
		this();
		Set<String> setOfKey = hmp.keySet();
		for (String s : setOfKey) {
			put(s, hmp.get(s));
		}
	}

	@Override
	public Integer put(String key, Integer value) {
		super.put(key, value);
		if (mapCntWord.containsKey(value)) {
			Set<String> wordsAtCount = mapCntWord.get(value);
			wordsAtCount.add(key);
		} else {
			Set<String> wordsAtCount = new HashSet<String>();
			wordsAtCount.add(key);
			mapCntWord.put(value, wordsAtCount);
		}
		return value;
	}

	public Integer count(String word) {
		Integer value = get(word);
		return value;
	}

	@Override
	public int size() {
		Set<String> setOfKey = keySet(); 
		int i = 0;
		for (String s : setOfKey) {
			i++;
		}
		return i;
	}

	public Collection<Number> counts() {
		Set<Integer> sortedCountSet = mapCntWord.keySet();
		Collection<Number> collectionCounts = new LinkedList<Number>();
		for (Integer i : sortedCountSet) {
			collectionCounts.add((Number) i);
		}
		return collectionCounts;
	}

	public Collection<String> words() {
		Set<Integer> sortedCountSet = mapCntWord.keySet();
		Collection<String> collectionStrings = new LinkedList<String>();
		for (Integer i : sortedCountSet) {
			Set<String> stringAtCount = mapCntWord.get(i);
			for (String s : stringAtCount) {
				collectionStrings.add(s);
			}
		}
		return collectionStrings;
	}

	public int rank(String word) {
		Integer count = get(word);
		int rk = size();
		Set<Integer> sortedCountSet = mapCntWord.keySet();
		for (Integer i : sortedCountSet) {
			Set<String> stringAtCount = mapCntWord.get(i);
			int currentSize = stringAtCount.size();
			if (i.equals(count)) {
				break;
			}
			rk -= currentSize;
		}
		return rk;
	}
}
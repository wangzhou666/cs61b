package ngordnet;
import java.util.Collection;
import java.util.TreeMap;
import java.util.Set;
import java.util.LinkedList;

public class TimeSeries<T extends Number> extends TreeMap<Integer, T> {

	public TimeSeries() {
		super();
	}

	public Collection<Number> years() {
		Set<Integer> setOfKeys = keySet();
		Collection<Number> collectionOfKeys = new LinkedList<Number>();
		for (Integer num : setOfKeys) {
			collectionOfKeys.add(num);
		}
		return collectionOfKeys;
	}

	public Collection<Number> data() {
		Set<Integer> setOfKeys = keySet();
		Collection<Number> collectionOfData = new LinkedList<Number>();
		for (Integer num : setOfKeys) {
			collectionOfData.add(get(num));
		}
		return collectionOfData;
	}

	public T get(Integer key) {
		return super.get(key);
	}

	public TimeSeries<Double> plus(TimeSeries x) {
		TimeSeries<Double> newSeries = new TimeSeries<Double>();
		Set<Integer> setOfThis = keySet();
		Set<Integer> setOfX = x.keySet();
		for (Integer num : setOfThis) {
			if (setOfX.contains(num)) {
				Double dVal = new Double(get(num).doubleValue() + x.get(num).doubleValue());
				newSeries.put(num, dVal);
			} else {
				Double dVal = new Double(get(num).doubleValue());
				newSeries.put(num, dVal);
			}
		}
		for (Integer num : setOfX) {
			if (setOfThis.contains(num)) {
				continue;
			} else {
				Double dVal = new Double(x.get(num).doubleValue());
				newSeries.put(num, dVal);
			}
		}
		return newSeries;
	}

	public TimeSeries<Double> dividedBy(TimeSeries x) {
		TimeSeries<Double> newSeries = new TimeSeries<Double>();
		Set<Integer> setOfKeys = keySet();
		Set<Integer> setOfKeysX = x.keySet();
		for (Integer num : setOfKeys) {
			if (!setOfKeysX.contains(num)) {
				continue;
			}
			Double dVal = new Double(get(num).doubleValue() / x.get(num).doubleValue());
			newSeries.put(num, dVal);
		}
		return newSeries;
	}
}
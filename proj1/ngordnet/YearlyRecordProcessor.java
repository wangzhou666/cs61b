package ngordnet;

import java.util.Set;
import java.lang.Integer;

public class YearlyRecordProcessor {

	public double process(YearlyRecord yr) {
		Set<String> yearlyWords = yr.keySet();
		long totalLength = 0;
		long totalCount = 0;
		for (String word : yearlyWords) {
			totalCount += ((Integer) yr.get(word)).intValue();
			totalLength += word.length() * ((Integer) yr.get(word)).intValue();
		}
		double result = totalLength / totalCount;
		return result;
	}
}
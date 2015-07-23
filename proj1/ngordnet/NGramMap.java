package ngordnet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;

public class NGramMap {

	private HashMap<Integer, YearlyRecord> mapYearToWordAndCount;
	private HashMap<String, TimeSeries<Integer>> mapWordToYearAndCount;
	private TimeSeries<Long> mapYearToTotalCount;

	public NGramMap(String file_words, String file_counts) {
		mapYearToWordAndCount = new HashMap<Integer, YearlyRecord>();
		mapWordToYearAndCount = new HashMap<String, TimeSeries<Integer>>();
		In dataWords = new In(file_words);
		String[] linesWords = dataWords.readAllLines();
		for (String s : linesWords) {
			Scanner sc = new Scanner(s);
			String currentWord = sc.next();
			Integer currentYear = new Integer(sc.nextInt());
			Integer currentCount = new Integer(sc.nextInt());
			if (mapYearToWordAndCount.containsKey(currentYear)) {
				YearlyRecord currentYearRecord = mapYearToWordAndCount.get(currentYear);
				currentYearRecord.put(currentWord, currentCount);
			} else {
				YearlyRecord currentYearRecord = new YearlyRecord();
				currentYearRecord.put(currentWord, currentCount);
				mapYearToWordAndCount.put(currentYear, currentYearRecord);
			}
			if (mapWordToYearAndCount.containsKey(currentWord)) {
				TimeSeries<Integer> wordHistory = mapWordToYearAndCount.get(currentWord);
				wordHistory.put(currentYear, currentCount);
			} else {
				TimeSeries<Integer> wordHistory = new TimeSeries<Integer>();
				wordHistory.put(currentYear, currentCount);
				mapWordToYearAndCount.put(currentWord, wordHistory);
			}
			sc.close();
		}
		dataWords.close();

		mapYearToTotalCount = new TimeSeries<Long>();
		In dataTotal = new In(file_counts);
		String[] linesCounts = dataTotal.readAllLines();
		for (String s : linesCounts) {
			String replaced = s.replaceFirst(",", " ");
			replaced = replaced.replaceFirst(",", " ");
			Scanner sc = new Scanner(replaced);
			Integer theYear = new Integer(sc.nextInt());
			Long totalCount = new Long(sc.nextLong());
			mapYearToTotalCount.put(theYear, totalCount);
			sc.close();
		}
		dataTotal.close();
	}

	public Integer countInYear(String word, int year) {
		TimeSeries<Integer> wordHistory = mapWordToYearAndCount.get(word);
		Integer yearInteger = new Integer(year);
		return wordHistory.get(yearInteger);
	}

	public YearlyRecord getRecord(int year) {
		return mapYearToWordAndCount.get(year);
	}

	public TimeSeries<Integer> countHistory(String word) {
		return mapWordToYearAndCount.get(word);
	}

	public TimeSeries<Long> totalCountHistory() {
		return mapYearToTotalCount;
	}

	public TimeSeries<Double> weightHistory(String word) {
		TimeSeries<Integer> wordHistory = mapWordToYearAndCount.get(word);
		return wordHistory.dividedBy(mapYearToTotalCount);
	}

	public TimeSeries<Double> summedWeightHistory(ArrayList<String> words) {
		TimeSeries<Double> summedWordHistory = new TimeSeries<Double>();
		for (String s : words) {
			TimeSeries<Integer> wordHistory = mapWordToYearAndCount.get(s);
			summedWordHistory = summedWordHistory.plus(wordHistory);
		}
		return summedWordHistory.dividedBy(mapYearToTotalCount);
	}


}
package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;

public class NgordnetUI {

	public static void main(String[] args) {
		WordNet wn = new WordNet("./p1data/wordnet/synsets.txt", "./p1data/wordnet/hyponyms.txt");
		NGramMap ngm = new NGramMap("./p1data/ngrams/words_that_start_with_q.csv", "./p1data/ngrams/total_counts.csv");
		int startDate = 1505;
		int endDate = 2008;
		while (true) {
            System.out.print("> ");
            String line = StdIn.readLine();
            String[] rawTokens = line.split(" ");
            String command = rawTokens[0];
            String[] tokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
            switch (command) {
                case "quit": 
                    return;
                case "help":
                    In in = new In("ngordnet/help.txt");
                    String helpStr = in.readAll();
                    System.out.println(helpStr);
                    break;  
                case "range": 
                    startDate = Integer.parseInt(tokens[0]); 
                    endDate = Integer.parseInt(tokens[1]);
                    System.out.println("Start date: " + startDate);
                    System.out.println("End date: " + endDate);
                    break;
                case "count":
                	String word = tokens[0];
                	int year = Integer.parseInt(tokens[1]);
                	System.out.println(ngm.countInYear(word, year));
                	break;
                case "hyponyms":
                	String wordh = tokens[0];
                	String[] theHyponyms = wn.hyponyms(wordh);
                	System.out.println("Hyponyms of " + wordh + ":");
                	for (String s : theHyponyms) {
                		System.out.println(s);
                	}
                	break;
                case "history":
                	for (String t : tokens) {
                		Plotter.plotCountHistory(ngm, t, startDate, endDate);
                	}
                	break;
                case "hypohist":
                	for (String t : tokens) {
                		String[] currentHyponyms = wn.hyponyms(t);
                		for (String s : currentHyponyms) {
                			Plotter.plotCountHistory(ngm, s, startDate, endDate);
                		}
                	}
                	break;
                case "wordlength":
                    YearlyRecordProcessor yrp = new YearlyRecordProcessor();
                    Plotter.plotProcessedHistory(ngm, startDate, endDate, yrp);
                    break;
                default:
                    System.out.println("Invalid command.");  
                    break;
            }
        }
	}
}
package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Set;

public class WordNet {

	private Digraph graphHyponyms;
	private HashMap<String, TreeSet<Integer>> tableSynsets;
	private HashMap<Integer, TreeSet<String>> tableSynsetsID;

	public WordNet(String file_synsets, String file_hyponyms) {
		In dataSynsets = new In(file_synsets);		
		In dataHyponyms = new In(file_hyponyms);
		String[] linesInSynsets = dataSynsets.readAllLines();
		String[] linesInHyponyms = dataHyponyms.readAllLines();
		
		graphHyponyms = new Digraph(linesInSynsets.length);

		for (String currentLine : linesInHyponyms) {
			String replaced = currentLine.replaceAll(",", " ");
			Scanner sc = new Scanner(replaced);
			int target = sc.nextInt();
			while (sc.hasNextInt()) {
				graphHyponyms.addEdge(sc.nextInt(), target);
			}
			sc.close();
		}
		dataHyponyms.close();

		tableSynsets = new HashMap<String, TreeSet<Integer>>();
		tableSynsetsID = new HashMap<Integer, TreeSet<String>>();

		for (String currentLine : linesInSynsets) {
			String replaced_1st = currentLine.replaceFirst(",", " ");
			Scanner sc = new Scanner(replaced_1st);
			int num = sc.nextInt();
			TreeSet<Integer> wordIDs = new TreeSet<Integer>();
			TreeSet<String> words = new TreeSet<String>();
			wordIDs.add(num);
			while (sc.hasNext()) {
				String currentToken = sc.next();
				if (currentToken.contains(",")) {
					currentToken = currentToken.replaceFirst(",", " ");
					Scanner scanLast = new Scanner(currentToken);
					currentToken = scanLast.next();
				}
				words.add(currentToken);
				if (tableSynsets.containsKey(currentToken)) {
					TreeSet<Integer> existIDs = tableSynsets.get(currentToken);
					existIDs.add(num);
					tableSynsets.remove(currentToken);
					tableSynsets.put(currentToken, existIDs);
				} else {
					tableSynsets.put(currentToken, wordIDs);
				}				
				
			}
			tableSynsetsID.put(num, words);
		}
		dataSynsets.close();

	}
 	
 	public boolean isNoun(String word) {
 		return tableSynsets.containsKey(word);
 	}

 	public String[] nouns() {
 		Set<String> setNouns = tableSynsets.keySet();
 		String[] allNouns = new String[setNouns.size()];
 		int i = 0;
 		for (String s : setNouns) {
 			allNouns[i] = s;
 			i++;
 		}
 		return allNouns;
 	}

 	public String[] hyponyms(String word) {
 		TreeSet<Integer> existIDs = tableSynsets.get(word);
 		TreeSet<Integer> reachable = new TreeSet<Integer>();
 		Digraph reversedGraph = graphHyponyms.reverse();
 		for (int id : existIDs) {
 			DirectedDFS dfdp = new DirectedDFS(reversedGraph, id);
 			for (int i = 0; i < graphHyponyms.V(); i++) {
	 			if (dfdp.marked(i) & !reachable.contains(i)) {
	 				reachable.add(i);
	 			}
 			}
 		}
 		TreeSet<String> hyposToReturn = new TreeSet<String>();
 		for (int id : reachable) {
 			TreeSet<String> words = tableSynsetsID.get(id);
 			for (String s : words) {
 				if (!hyposToReturn.contains(s)) {
 					hyposToReturn.add(s);
 				}
 			}
 		}
 		String[] arrayToReturn = new String[hyposToReturn.size()];
 		int i = 0;
 		for (String s :	hyposToReturn) {
 			arrayToReturn[i] = s;
 			i++;
 		}		
 		return arrayToReturn;
	}
}
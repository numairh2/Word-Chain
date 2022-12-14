import java.util.*;
import java.io.*;
public class WordChain {
	private HashSet<String> dictionary;
	private HashSet<String> used;
	private Queue<Stack<String>> chain;
	private boolean solvable;
	private String start;
	private String end;
	/**
	 * Initializes the ArrayList with a start and end words.
	 */
	public WordChain(String start, String end) {
		this.dictionary = new HashSet<String>();
		this.used = new HashSet<String>();
		this.chain = new LinkedList<>();
		this.start = start.toUpperCase();
		this.end = end.toUpperCase();
		this.solvable = start.length() == end.length();
	}
	/**
	 * Traverses through the dictionary.txt and adds it to the HashSet
	 * @param filename name of the dictionary.txt
	 * @throws FileNotFoundException if File not found
	 */
	public void loadDictionary(String filename) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File(filename));
		this.dictionary = new HashSet<String>();
		int len = start.length();
		while(sc.hasNext())
		{
			String word = sc.nextLine();
			if(word.length() == len) {
				dictionary.add(word);
			}
			
		}
		sc.close();
	}
	
	/**
	 * Traverses through the alphabet and finds the word that differ by one character 
	 * @param str word that needs to be differ by one character
	 * @return a list of neighbors 
	 */
	public ArrayList<String> findNeighbors(String str)
	{
		ArrayList<String> neighbors = new ArrayList<String>();
		
		int len = str.length();
		for (int i=0; i<len; i++) 
		{
			for(char ch='A'; ch<='Z'; ch++)
			{
				StringBuilder sb = new StringBuilder(str);
				sb.setCharAt(i, ch);
				String strCurrent = sb.toString();
				
				if(!(strCurrent.equals(str) || this.used.contains(strCurrent)) && this.dictionary.contains(strCurrent))
				{
					neighbors.add(strCurrent);
					this.used.add(strCurrent);
					
				}
			}
		}
		
		return neighbors;
	}
	/**
	 * Determines if the chain is possible to solve.
	 * @return  if chain is possible to solve.
	 */
	public boolean isSolved() {
		return solvable;
	}
	
	/**
	 * Tries to go through the Queue of Stacks to find the shortest path.
	 * @return the shortest stack of words
	 */
	public Stack<String> solve() 
	{	
		Stack<String> startStack = new Stack<String>();
		if(this.dictionary.contains(start) && this.solvable)
		{
			startStack.add(this.start);
			this.chain.offer(startStack);
			
			while(!this.chain.isEmpty())
			{
				Stack<String> current = this.chain.poll();
				
				if(current.lastElement().equals(this.end))
				{
					this.solvable = true;
					return current;
				}
				
				ArrayList<String> neighbors = findNeighbors(current.peek());
				
				for(int i=0; i<neighbors.size(); i++)
				{
					Stack<String> copy = new Stack<String>();
					copy.addAll(current);
					copy.add(neighbors.get(i));
					this.chain.offer(copy);
				}
			}
		
		}
		this.solvable = false;
		return null;
	}
	
}//class ends

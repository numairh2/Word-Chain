import java.util.*;

import javax.management.timer.Timer;

import java.io.*;
public class WordChainRunner {
	public static void main(String[] args) 
	{
		
		Scanner in = null;
		try
		{
			double starting = System.currentTimeMillis();
			in = new Scanner(new File("input.txt"));
			while (in.hasNext())
			{
				
				String start = in.next();
				String end = in.next();
				WordChain chain = new WordChain(start,end);// (start,end)
				chain.loadDictionary("dictionary.txt");
				Stack<String> wordChain = chain.solve();
				if(chain.isSolved())
				{
					System.out.println("Found a ladder! >>> " + wordChain);
				}
				else {
					System.out.println("No ladder between " + start.toUpperCase() + " and  " + end.toUpperCase());
				}
				
			}
			System.out.println("Time: " + (System.currentTimeMillis()- starting)/1000 + " seconds");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Wrong file name!");
			System.exit(0);
		}
		
		
	}
		
}

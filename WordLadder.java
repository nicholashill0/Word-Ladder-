package student;

import java.util.*;
import java.io.*;

public class WordLadder
{
	public static List<String> solve(String dictfile, String start, String end)
	{
		HashMap<String, String> hash = new HashMap<>();
		
		try
		{
			File dict = new File(dictfile);
			Scanner scan = new Scanner(dict);
			while (scan.hasNext())
				hash.put(scan.next(), null);
			scan.close();
		}
		catch (IOException e)
		{
			return null;
		}

		Queue<String> Q = new LinkedList<>();
		Q.add(start);
		hash.put(start, "");

		while (!Q.isEmpty())
		{
			String val = Q.poll();

			if (val.equals(end))
			{
				return path(hash, start, end);
			}

			for (int i = 0; i < val.length(); i++)
			{
				for (char c = 'a'; c <= 'z'; c++)
				{
					if (c != val.charAt(i))
					{
						String near = val.substring(0, i) + c + val.substring(i + 1);

						if (hash.containsKey(near) && hash.get(near) == null)
						{
							hash.put(near, val);
							Q.add(near);
						}
					}
				}
			}
		}

		return null;
	}

	private static List<String> path(HashMap<String, String> hash, String start, String end)
	{
		LinkedList<String> path = new LinkedList<>();
		String val = end;

		while (!val.equals(start))
		{
			path.addFirst(val);
			val = hash.get(val);

		}
		path.addFirst(start);
		return path;
	}
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the start word: ");
		String start = scan.next();
		System.out.print("Enter the end word: ");
		String end = scan.next();

		List<String> solution = solve(args[0], start, end);
		if (solution == null)
			System.out.println("Impossible!");
		else
		{
			System.out.println("Possible!\n");
			for (String word : solution)
				System.out.println(word);
		}
	}
}
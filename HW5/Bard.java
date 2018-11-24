/*
 * This program looks through Shakespeare's Bard and also takes in an input text file
 * The program can determine how many times a word was used by Shakespeare and for any 
 * word of length l and number k, the program can figure out the k most frequent words 
 * of length l. 
 *
 * Written by Samuel Shin (sayshin@ucsc.edu)
 */

import java.util.*;
import java.io.*;

public class Bard
{
	static PrintWriter out;
	static HashMap<String, Integer> hm1 = new HashMap<String, Integer>();//hashmap for the word frequency query
	static HashMap<String, Integer> hm2 = new HashMap<String, Integer>();//hashmap for finding k most frequent words of lenght l

	public static void main(String[] args) throws FileNotFoundException
	{
		out = new PrintWriter("analysis.txt");
		File f = new File("input.txt");
		Scanner s = new Scanner(f);
		int length = 0;
		int freq = 0;
		stringQuery();//puts all words in the bard as key in hashmap and increments the value for every repeat of each word
		while (s.hasNextLine())
		{
			String str = s.nextLine();
			Scanner line = new Scanner(str);
			int count = 0;
			if (Character.isDigit(str.charAt(0)))//if first char of input is a number 
			{
				while (line.hasNextInt())//then input has two ints 
                        	{
                                	
                                        if (count == 0)
                                        {
                                               	length = line.nextInt();//first number is the word length (l)
                                               	count++;
                                       	}
                                       	else
                                       	{	
                                               	freq = line.nextInt();//second number is how many numbers you want to print (k)
						numberQuery(length);//puts the words of desired length in a hashmap
						for (int a = 0; a < freq; a++)
						{
							Iterator<Map.Entry<String, Integer>> itr = hm2.entrySet().iterator();//create iterator to go over hashmap
							int max = (Collections.max(hm2.values()));//gets the max value in hashmap
							while (itr.hasNext())
							{
								Map.Entry<String, Integer> entry =  itr.next();//get next key pair value to look at
								if (entry.getValue() == max)//if the same as max
								{
									out.print(entry.getKey() + " ");//print it out
									out.flush();
									itr.remove();//remove the key pair value from hashmap so no repeats happen
								}
							}
						}
					}
				}
			}
			else//if first char is not a number, then its a word to look for 
			{
				if (hm1.containsKey(str))//if the word you're looking for is in the hashmap
				{
					out.print(hm1.get(str));//print out the value or how many times it appears in the bard
					out.flush();
				}
				else 
				{
					out.print("0");//if not in the hashmap, print 0
					out.flush();
				}
			}
		out.println();
		out.flush();
		}
	}

	public static void stringQuery() throws FileNotFoundException
	{
		File file = new File("shakespeare.txt");
		Scanner scan = new Scanner(file);

		while (scan.hasNextLine())
		{
			String line = scan.nextLine().trim();//read line by line and get rid of leading and trailing white spaces
			if (line.isEmpty())//skip line if empty
			{
				continue;
			}
	                String[] token = line.split("\\s+");
		
			for (int i = 0; i < token.length; i++)
			{
				token[i] = token[i].replaceAll("[^\\'\\-\\w\\s]", "");//parse text so that all things that are not ', -, alphanumeric, and white space 
				//are cut from the text
				if ("".equals(token[i]))//double security for empty line
                                {
                                        break;
                                }
				if (token[i].length() == 0)//triple security
				{
					break;
				}
				
				if (token[i].equals(token[i].toUpperCase()))//if the word is in all caps skip
				{
					continue;	
				}				

				token[i] = token[i].toLowerCase();//change all words into all lowercase 
				token[i] = token[i].trim();
				
				if (hm1.containsKey(token[i]))//if the word is already in the hashmap
				{
					hm1.put(token[i], hm1.get(token[i]) + 1);//keep same key but increment value by 1
				}
				else 
				{
					int value = 1;
					hm1.put(token[i], value);//insert a new fresh key with value 1
				}
				
			}
		}
	}
	
	public static void numberQuery(int len) throws FileNotFoundException//uses same exact logic as stringQuery except only allows words with length len enter hm
	{
		File file = new File("shakespeare.txt");
                Scanner scan = new Scanner(file);
		
		while (scan.hasNextLine())
                {
                        String line = scan.nextLine().trim();
                        if (line.isEmpty())
                        {
                                continue;
                        }
                        String[] token = line.split("\\s+");

                        for (int i = 0; i < token.length; i++)
                        {
				token[i] = token[i].replaceAll("[^\\'\\-\\w\\s]", "");
				if ("".equals(token[i]))
                                {
                                        break;
                                }
                                if (token[i].length() == 0)
                                {
                                        break;
                                }

                                if (token[i].equals(token[i].toUpperCase()))
                                {
                                        continue;
                                }

                                token[i] = token[i].toLowerCase();
                                token[i] = token[i].trim();

				if (token[i].length() == len)//only change from stringQuery
				{
                                	if (hm2.containsKey(token[i]))
                                	{
                                        	hm2.put(token[i], hm2.get(token[i]) + 1);
                                	}
                                	else
                                	{
                                        	int value = 1;
                                        	hm2.put(token[i], value);
                                	}
				}
                        }
		}
	}
}

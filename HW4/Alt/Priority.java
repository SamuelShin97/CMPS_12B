/*
 * This program implements a priority queue using a linked list that 
 * takes in item objects and stores them in a priority queue inserting from the tail.
 * This program also deletes the item with the highest priority and prints out the ids of the items
 * that were deleted
 *
 * Written by Samuel Shin (sayshin@ucsc.edu)
 */

import java.io.*;
import java.util.*;

public class Priority
{
	public static Node head;
	public static Node tail;
	static PrintWriter out;

	public Priority()
	{
		head = null;
		tail = null;
	}

	public static void insert(Item a)//inserts items at the tail of the queue
	{
		if (tail == null)//if the list is empty
		{
			Node latest = new Node(a);//creates a new node for the item
			head = latest;//both head and tail point to the same node since there's only one node in the queue at this point
			tail = latest; 
		}
	       	else if (head == tail)//if there is only one node in the queue
		{
			Node latest = new Node(a);
			head.next = latest;//connect the two nodes together
			tail = latest;
		}
		else 
		{
			Node latest = new Node(a);//if the queue has 2 or more nodes, then simply add to the tail
			tail.next = latest;
			tail = latest;
		}
	}

	public static Item findMax()//finds the item with the max priority and returns it
	{
		Node max = head;//start max at head
		if (tail == null)//if the queue is empty return null
		{
			return null;
		}
		if (head == tail)//if there is only one item in the queue
		{
			return max.item;
		}
		
		Node current = head.next;//if code reaches here then head has a next
		while (current != null)
		{
			if (max.item.priority >= current.item.priority)//if max's priority is greater equal to next item in list move current along 
			{
				current = current.next;
			}
			else 
			{
				max = current;//if not then max gets current and current moves along
				current = current.next;
			}
			
 		}
		return max.item;	
	}

	public static Item delete(Item toDelete)//deletes item in the queue and returns the item that was deleted
	{
		
		if (toDelete == null)//if queue is empty return null
		{
			return null;
		}
		
		if (toDelete == head.item)//if the item to delete is at the head
		{
			if (head == tail)//if the item is the only item in the queue
			{
				head = null;
				tail = null;//empty the queue
				return toDelete;
			}
			else 
			{
				head = head.next;//simply delete the head
				return toDelete;
			}
		}
		Node current = head;
		Node previous = null;
		while (current != null && current.item != toDelete)//deletes the item if anywhere else in the queue
		{
			previous = current;
			current = current.next;
			if (current.item == toDelete)
			{
				previous.next = current.next;
				return current.item;
			}
		}
		return null;	
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		out = new PrintWriter("output.txt");
		File file = new File("input.txt");
		Scanner scan = new Scanner(file);
		ArrayList<Item> deleted = new ArrayList<Item>();//stores the deleted items in an arraylist
		int isEmpty = 0;//condition to check if queue is empty
		while (scan.hasNextLine())
		{
			String line = scan.nextLine().trim();
			String[] token = line.split("\\s+");//tokenizes each line of input file into an array
			int count = 0;
			int x = 0;
			int y = 0;
			
			for (int i = 0; i < token.length; i++)//gets rid of the "( , )" characters in the tokens
			{
				if (token[i].equals("d"))
				{
					 token[i] = token[i];
				}
				else 
				{
					token[i] = token[i].replaceAll("\\D+", "");
				}
			}
			
			for (int i = 0; i < token.length; i++)//goes through each token in array
			{
				if (token[i].equals("d"))
				{
					Item d = findMax();//stores max item
					Item del = delete(d);//deletes and stores deleted item
					if (del == null)//if deleted item is null
					{
						isEmpty++;//then the queue is empty
					}
					else 
					{
						deleted.add(del);//add to arraylist of deleted items
					}
				}
				else 
				{
					if (count == 0)
					{
						x = Integer.parseInt(token[i]);//stores id of item
						count++;
					}
					else if (count == 1)
					{
						y = Integer.parseInt(token[i]);//stores priority of item
						Item toInsert = new Item(x, y);
						insert(toInsert);//make new item object and insert it into queue
						x = 0;
						y = 0;
						count = 0;//reset variables
					}
				 }
			}
			Iterator<Item> itr = deleted.iterator();
			while (itr.hasNext())
			{
				Item i = itr.next();
				out.print(i.id + " ");
				out.flush();
			}
			if (isEmpty != 0)//if not zero then the queue is empty
			{
				out.print("null");
				out.flush();
			}
        		out.println();
        		out.flush();
			deleted.clear();
			head = null;
			tail = null;
			isEmpty = 0;
		}
	out.close();	
	}
}

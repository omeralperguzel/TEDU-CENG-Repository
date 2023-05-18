//-----------------------------------------------------
//Title: Queue
//Author: Ömer Alper Güzel
//ID: 16057474442
//Section: 2
//Assignment: 1 Q2
//Description: This is a Java program that allows the user to create and manage queue by using the strings.
//-----------------------------------------------------

public class Queue{

	private Node first, last;
	
	public Queue() {
		first = null;
		last = null;
	}
	
	//The program will add a String element in the queue at the end.
	//c is an element of String type.
    
	public void enqueue(char c) {
		Node oldLast = last;
		last = new Node();
		last.item = c;
		last.next = null;
		if (isEmpty()) 
			first = last;
		else 
			oldLast.next = last;
	}
	
	//The program will remove the element from the queue.

	public String dequeue() {
		char item = first.item;
		first = first.next;
		if (isEmpty()) last = null;
		String stringitem = Character.toString(item);
        return stringitem;
	}
	
	//This part of the program checks to see if the queue is empty. It will return true value if queue is empty and it will return false if queue is not empty.

	public boolean isEmpty() {
		return (first == null);
	}
	
	//Node class represents a node used to store each element of the queue. 

	private class Node {
		char item;
		Node next;
	}
}
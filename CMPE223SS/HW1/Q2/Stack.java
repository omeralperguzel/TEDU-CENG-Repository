//-----------------------------------------------------
//Title: Stack
//Author: Ömer Alper Güzel
//ID: 16057474442
//Section: 2
//Assignment: 1 Q2
//Description: This is a Java program that allows the user to create and manage stack by using the linked list implementation method.
//-----------------------------------------------------

public class Stack{
    private Node first = null;
    private class Node{ // nested class
        char item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }       
        
    public void push(char c){
        Node oldfirst = first;
        first = new Node();
        first.item = c;
        first.next = oldfirst;
    }
    
    public String pop(){
        char item = first.item;
        first = first.next;
        String stringitem = Character.toString(item);
        return stringitem;
    }
}
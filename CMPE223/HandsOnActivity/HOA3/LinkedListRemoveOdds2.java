package CMPE223.HandsOnActivity.HOA3;

import java.io.*;

public class Node {
    int item;
    Node next;
    Node(int d)
    {
        item = d;
        next = null;
    }
}
 
public class SLinkedList {
    private Node head = null;

    void removeOdds() {
       if (head == null) return;
 
       Node prev = head;
       Node now = head.next;
 
       while (prev != null && now != null) {     
           prev.next = now.next;
           now = null;

           prev = prev.next;
           if (prev != null) 
              now = prev.next;
       }
    }                 
 
    public void push(int new_data) {

        Node new_node = new Node(new_data);
  
        new_node.next = head;

        head = new_node;
    }

}


package CMPE223.HandsOnActivity.HOA3;

import java.io.*;

public class IdenticalLinkedLists {
    public static void main(String[] args){

    }
}

/*
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

    boolean areIdentical(SLinkedList listb)
    {
        Node a = this.head, b = listb.head;
        while (a != null && b != null) {
            if (a.item != b.item)
                return false;

            a = a.next;
            b = b.next;
        }

        return (a == null && b == null);
    }
 
    void push(int new_data){

        Node new_node = new Node(new_data);
 
        new_node.next = head;

        head = new_node;
    }
 
    public static void main(String args[])
    {
        SLinkedList linkedlist1 = new SLinkedList();
        SLinkedList linkedlist2 = new SLinkedList();
 
        linkedlist1.push(1);
        linkedlist1.push(2);
        linkedlist1.push(3);
 
        linkedlist2.push(1);
        linkedlist2.push(2);
        linkedlist2.push(3);
 
        if (linkedlist1.areIdentical(linkedlist2) == true)
            System.out.println("They are identical ");
        else
            System.out.println("They are not identical ");
    }
} */
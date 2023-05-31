//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 3 Q2
//Description: This is a Java program that allows the user to use nodes in a binary search tree.
//-----------------------------------------------------

package CMPE223SS.HW3.Q2;

public class Node {

    //This is the Node class used for creating a node in the binary search tree
    Employee employee;
    Node left;
    Node right;

    public Node(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }
    
}

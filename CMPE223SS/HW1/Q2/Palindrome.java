//-----------------------------------------------------
//Title: Palindrome
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 1 Q2
//Description: This is a Java program that allows the user to check if the string is a palindrome or not.   
//-----------------------------------------------------

import java.util.Scanner;

public class Palindrome{

    public static void main(String[] args) {

    	System.out.print("Enter a string:");
        try (Scanner in = new Scanner(System.in)) {
            String inputString = in.nextLine();
            Stack stack = new Stack();
            Queue queue = new Queue();

            //Comparing by stack using linked list implementation
            for (int i = 0; i < inputString.length(); i++) {
                stack.push(inputString.charAt(i));
            }

            String reverseStringStack = "";
            String reverseStringQueue = "";

            while (!stack.isEmpty()) {
                reverseStringStack = reverseStringStack+stack.pop();
            }

            //Comparing by queue using strings
            for (int i = inputString.length()-1; i >=0; i--) {
                queue.enqueue(inputString.charAt(i));
            }
    
            while (!queue.isEmpty()) {
                reverseStringQueue = reverseStringQueue+queue.dequeue();
            }

            //Final comparison for printing the result
            if (inputString.equals(reverseStringStack) && inputString.equals(reverseStringQueue))
                System.out.println("It is a palindrome string.");
            else
                System.out.println("It is not a palindrome string.");

    }
 }
}
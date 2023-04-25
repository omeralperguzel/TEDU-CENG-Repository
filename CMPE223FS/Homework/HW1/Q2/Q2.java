//-----------------------------------------------------
// Author: Ömer Alper Güzel
// ID: 16057474442
// Section: 5
// Assignment: 1/Q2
//-----------------------------------------------------

package CMPE223FS.Homework.HW1.Q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.Scanner;

public class Q2 {

    public static void main(String[] args) throws FileNotFoundException{
        //First we should get our file from the user input
        System.out.println("Input filename:");
        Scanner keyboard = new Scanner(System.in);
        File file = new File(keyboard.nextLine());
        Scanner filescan = new Scanner(file);

        //Then create a stack according to our file
        Stack<Integer> data = new Stack<Integer>();
        while (filescan.hasNext()){
            data.add(filescan.nextInt());
        }
        System.out.println(data.toString());

        //Now create new empty stack
        Stack<Integer> endoutput = new Stack<Integer>();

        //Now we have to fill our empty stack
        for (int x = 0; x < data.size(); x++){
            //We should initialize a number to keep how many larger numbers are here after our processes.
            int largeCounter = 0;
            for (int y = x; y >= 0; y--) {
                //From the first number to last number in the stack we should check if the previous number was larger than the number we selected now.
                // If yes increment the flag, or else we should break our loop.
                if(data.get(x) <= data.get(y)) largeCounter++;
                else break;

             }
            endoutput.push(largeCounter);
            }
        System.out.println(endoutput.toString());
        }
    }


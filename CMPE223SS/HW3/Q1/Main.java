//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 3 Q1
//Description: This is a Java program that allows the user to use several operations on a family tree.
//-----------------------------------------------------

package CMPE223SS.HW3.Q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();
        FamilyTree familyTree = createFamilyTree(filename);
        while (true) {
            // This is the first operation used for asking the user to enter the operation code.
            System.out.print("Enter operation code: ");
            int operationCode = scanner.nextInt();
            //It is used for printing all descendants of a person.
            if (operationCode == 1) {
                System.out.print("Enter ID: ");
                int id = scanner.nextInt();
                familyTree.printAllDescendants(id);
            } 
            // It is used for checking if a person is an ancestor of another person.
            else if (operationCode == 2) {
                System.out.print("Enter IDs: ");
                int id1 = scanner.nextInt();
                int id2 = scanner.nextInt();
                System.out.println(familyTree.checkAncestor(id1, id2));
            } 
            // It is used for checking if a person is a descendant of another person.
            else if (operationCode == 3) {
                System.out.print("Enter IDs: ");
                int id1 = scanner.nextInt();
                int id2 = scanner.nextInt();
                System.out.println(familyTree.checkDescendant(id1, id2));
            } 
            // It is used for checking if two people are siblings.
            else if (operationCode == 4) {
                System.out.print("Enter IDs: ");
                int id1 = scanner.nextInt();
                int id2 = scanner.nextInt();
                System.out.println(familyTree.checkSibling(id1, id2));
            } 
            // It is used for finding the oldest common relative of two people.
            else if (operationCode == 5) {
                System.out.print("Enter IDs: ");
                int id1 = scanner.nextInt();
                int id2 = scanner.nextInt();
                System.out.println(familyTree.findOldestCommonRelative(id1, id2));
            } 
            // It is used for stopping the program.
            else if (operationCode == 6) {
                System.out.println("Stopped!");
                break;
            }
        }
    }

    // This method is used for creating a family tree from a file.
    private static FamilyTree createFamilyTree(String filename) {
        Person root = null;
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String[] partsleft = parts[0].split(" ");
                String[] partsright = parts[1].split(" ");                
                //System.out.println(partsleft[0]);
                //System.out.println(partsleft[1]);
                //System.out.println(partsright[0]);
                //System.out.println(partsright[1]);
                String name1 = partsleft[0];
                int id1 = Integer.parseInt(partsleft[1]);
                String name2 = partsright[0];
                int id2 = Integer.parseInt(partsright[1]);
                if (root == null) {
                    root = new Person(name1, id1);
                    root.addChild(new Person(name2, id2));
                } else {
                    Person person1 = findPerson(root, id1);
                    if (person1 != null) {
                        person1.addChild(new Person(name2, id2));
                    } else {
                        Person person2 = findPerson(root, id2);
                        if (person2 != null) {
                            person2.addChild(new Person(name1, id1));
                        }
                    }
                }
            }
            scanner.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return new FamilyTree(root);
    }

    // This method is used for finding a person in a family tree.
    private static Person findPerson(Person person, int id) {
        if (person.getId() == id) {
            return person;
        }
        for (Person child : person.getChildren()) {
            Person foundPerson = findPerson(child, id);
            if (foundPerson != null) {
                return foundPerson;
            }
        }
        return null;
    }
}
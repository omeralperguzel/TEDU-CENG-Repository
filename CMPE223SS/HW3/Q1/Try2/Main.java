package CMPE223SS.HW3.Q1.Try2;

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
            System.out.print("Enter operation code: ");
            int operationCode = scanner.nextInt();
            if (operationCode == 1) {
                System.out.print("Enter ID: ");
                int id = scanner.nextInt();
                familyTree.printAllDescendants(id);
            } else if (operationCode == 2) {
                System.out.print("Enter IDs: ");
                int id1 = scanner.nextInt();
                int id2 = scanner.nextInt();
                System.out.println(familyTree.checkAncestor(id1, id2));
            } else if (operationCode == 3) {
                System.out.print("Enter IDs: ");
                int id1 = scanner.nextInt();
                int id2 = scanner.nextInt();
                System.out.println(familyTree.checkDescendant(id1, id2));
            } else if (operationCode == 4) {
                System.out.print("Enter IDs: ");
                int id1 = scanner.nextInt();
                int id2 = scanner.nextInt();
                System.out.println(familyTree.checkSibling(id1, id2));
            } else if (operationCode == 5) {
                System.out.print("Enter IDs: ");
                int id1 = scanner.nextInt();
                int id2 = scanner.nextInt();
                System.out.println(familyTree.findOldestCommonRelative(id1, id2));
            } else if (operationCode == 6) {
                System.out.println("Stopped!");
                break;
            }
        }
    }

    private static FamilyTree createFamilyTree(String filename) {
        Person root = null;
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String name1 = parts[0];
                int id1 = Integer.parseInt(parts[1]);
                String name2 = parts[2];
                int id2 = Integer.parseInt(parts[3]);
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
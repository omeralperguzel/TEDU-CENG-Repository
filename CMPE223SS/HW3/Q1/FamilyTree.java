//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 3 Q1
//Description: This is a Java program that allows the user to create a family tree, find a person in the tree and print all the descendants of a person.
//-----------------------------------------------------

package CMPE223SS.HW3.Q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class FamilyTree {
    Person root;

    public FamilyTree(Person root) {
        this.root = root;
    }

    // This is the createFamilyTree method used for creating a family tree from a file
    public static FamilyTree createFamilyTree(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        Map<Integer, Person> idToPerson = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" ");
            int id1 = Integer.parseInt(parts[0]);
            String name1 = parts[1];
            int id2 = Integer.parseInt(parts[2]);
            String name2 = parts[3];
            Person person1 = idToPerson.getOrDefault(id1, new Person(name1, id1));
            Person person2 = idToPerson.getOrDefault(id2, new Person(name2, id2));
            person2.children.add(person1);
            idToPerson.put(id1, person1);
            idToPerson.put(id2, person2);
        }
        scanner.close();
        return new FamilyTree(idToPerson.get(0));
    }

    // This is the printAllDescendants method used for printing all the descendants of a person
    public void printAllDescendants(int id) {
        Person person = findPerson(root, id);
        if (person != null) {
            List<Person> descendants = new ArrayList<>();
            collectDescendants(person, descendants);
            Collections.sort(descendants, Comparator.comparingInt(p -> p.id));
            for (Person descendant : descendants) {
                System.out.println(descendant.name);
            }
        }
    }

    // This is the checkAncestor method used for checking if a person is an ancestor of another person
    public boolean checkAncestor(int id1, int id2) {
        Person person1 = findPerson(root, id1);
        Person person2 = findPerson(root, id2);
        if (person1 != null && person2 != null) {
            return isAncestor(person1, person2);
        }
        return false;
    }

    // This is the checkDescendant method used for checking if a person is a descendant of another person
    public boolean checkDescendant(int id1, int id2) {
        Person person1 = findPerson(root, id1);
        Person person2 = findPerson(root, id2);
        if (person1 != null && person2 != null) {
            return isDescendant(person1, person2);
        }
        return false;
    }

    // This is the checkSibling method used for checking if a person is a sibling of another person
    public boolean checkSibling(int id1, int id2) {
        Person person1 = findPerson(root, id1);
        Person person2 = findPerson(root, id2);
        if (person1 != null && person2 != null) {
            Person parent1 = findParent(root, person1);
            Person parent2 = findParent(root, person2);
            return parent1 != null && parent2 != null && parent1 == parent2;
        }
        return false;
    }

    // This is the findOldestCommonRelative method used for finding the oldest common relative of two people
    public String findOldestCommonRelative(int id1, int id2) {
        Person person1 = findPerson(root, id1);
        Person person2 = findPerson(root, id2);
        if (person1 != null && person2 != null) {
            Set<Person> ancestors = new HashSet<>();
            collectAncestors(person1, ancestors);
            while (person2 != null) {
                if (ancestors.contains(person2)) {
                    return person2.name;
                }
                person2 = findParent(root, person2);
            }
        }
        return null;
    }

    // This is the findPerson method used for finding a person in the tree
    private Person findPerson(Person person, int id) {
        if (person.id == id) {
            return person;
        }
        for (Person child : person.children) {
            Person found = findPerson(child, id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    // This is the collectDescendants method used for collecting all the descendants of a person
    private void collectDescendants(Person person, List<Person> descendants) {
        descendants.add(person);
        for (Person child : person.children) {
            collectDescendants(child, descendants);
        }
    }

    // This is the isAncestor method used for checking if a person is an ancestor of another person
    private boolean isAncestor(Person ancestor, Person person) {
        if (ancestor == person) {
            return true;
        }
        for (Person child : ancestor.children) {
            if (isAncestor(child, person)) {
                return true;
            }
        }
        return false;
    }

    // This is the isDescendant method used for checking if a person is a descendant of another person
    private boolean isDescendant(Person person, Person descendant) {
        if (person == descendant) {
            return true;
        }
        for (Person child : person.children) {
            if (isDescendant(child, descendant)) {
                return true;
            }
        }
        return false;
    }

    // This is the findParent method used for finding the parent of a person
    private Person findParent(Person parent, Person child) {
        for (Person p : parent.children) {
            if (p == child) {
                return parent;
            }
            Person found = findParent(p, child);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    // This is the collectAncestors method used for collecting all the ancestors of a person
    private void collectAncestors(Person person, Set<Person> ancestors) {
        Person parent = findParent(root, person);
        if (parent != null) {
            ancestors.add(parent);
            collectAncestors(parent, ancestors);
        }
    }
}
//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 3 Q1
//Description: This is a Java program that allows the user to perform sorting and find the minimum and maximum difference between two numbers in an array.
//-----------------------------------------------------


package CMPE223SS.HW3.Q1.Try1;

import java.util.ArrayList;

class FamilyTree {
    Person root;

    public FamilyTree(Person root) {
        this.root = root;
    }

    public Person getRoot() {
        return this.root;
    }

    public void printAllDescendants(int id) {
        Person person = findPerson(this.root, id);
        if (person != null) {
            ArrayList<Person> descendants = new ArrayList<>();
            getDescendants(person, descendants);
            descendants.sort((p1, p2) -> p1.getId() - p2.getId());
            for (Person descendant : descendants) {
                System.out.print(descendant.getName() + " ");
            }
            System.out.println();
        }
    }

    public boolean checkAncestor(int id1, int id2) {
        Person person1 = findPerson(this.root, id1);
        Person person2 = findPerson(this.root, id2);
        if (person1 != null && person2 != null) {
            return isAncestor(person1, person2);
        }
        return false;
    }

    public boolean checkDescendant(int id1, int id2) {
        Person person1 = findPerson(this.root, id1);
        Person person2 = findPerson(this.root, id2);
        if (person1 != null && person2 != null) {
            return isDescendant(person1, person2);
        }
        return false;
    }

    public boolean checkSibling(int id1, int id2) {
        Person person1 = findPerson(this.root, id1);
        Person person2 = findPerson(this.root, id2);
        if (person1 != null && person2 != null) {
            return isSibling(person1, person2);
        }
        return false;
    }

    public String findOldestCommonRelative(int id1, int id2) {
        Person person1 = findPerson(this.root, id1);
        Person person2 = findPerson(this.root, id2);
        if (person1 != null && person2 != null) {
            return findOldestCommonRelative(person1, person2).getName();
        }
        return "";
    }

    private Person findPerson(Person person, int id) {
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

    private void getDescendants(Person person, ArrayList<Person> descendants) {
        for (Person child : person.getChildren()) {
            descendants.add(child);
            getDescendants(child, descendants);
        }
    }

    private boolean isAncestor(Person ancestor, Person person) {
        if (ancestor == person) {
            return true;
        }
        for (Person child : ancestor.getChildren()) {
            if (isAncestor(child, person)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDescendant(Person person, Person descendant) {
        if (person == descendant) {
            return true;
        }
        for (Person child : person.getChildren()) {
            if (isDescendant(child, descendant)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSibling(Person person1, Person person2) {
        if (person1 == person2) {
            return false;
        }
        for (Person child : person1.getChildren()) {
            if (isDescendant(child, person2)) {
                return true;
            }
        }
        return false;
    }

    private Person findOldestCommonRelative(Person person1, Person person2) {
        if (isAncestor(person1, person2)) {
            return person1;
        }
        if (isAncestor(person2, person1)) {
            return person2;
        }
        return findOldestCommonRelative(person1, person2, this.root);
    }

    private Person findOldestCommonRelative(Person person1, Person person2, Person current) {
        boolean person1IsDescendant = isDescendant(current, person1);
        boolean person2IsDescendant = isDescendant(current, person2);
        if (person1IsDescendant && person2IsDescendant) {
            for (Person child : current.getChildren()) {
                if (isDescendant(child, person1) && isDescendant(child, person2)) {
                    return findOldestCommonRelative(person1, person2, child);
                }
            }
            return current;
        }
        return null;
    }
}
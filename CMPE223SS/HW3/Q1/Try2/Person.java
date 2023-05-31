//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 3 Q1
//Description: This is a Java program that allows the user to create new people and use them in a tree.
//-----------------------------------------------------

package CMPE223SS.HW3.Q1.Try2;

import java.util.ArrayList;
import java.util.List;

class Person {
    // First, we have to create our name, id and children variables
    String name;
    int id;
    List<Person> children;

    // This is the constructor of the class Person
    public Person(String name, int id) {
        this.name = name;
        this.id = id;
        this.children = new ArrayList<>();
    }

    // This is the addChild method used for adding a child to the children list
    public void addChild(Person child) {
        this.children.add(child);
    }

    // This is the getChildren method used for getting the children list
    public List<Person> getChildren() {
        return this.children;
    }

    // This is the getName method used for getting the name of the person
    public String getName() {
        return this.name;
    }

    // This is the getId method used for getting the id of the person
    public int getId() {
        return this.id;
    }
}
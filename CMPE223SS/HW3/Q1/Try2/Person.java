package CMPE223SS.HW3.Q1.Try2;

import java.util.ArrayList;
import java.util.List;

class Person {
    String name;
    int id;
    List<Person> children;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
        this.children = new ArrayList<>();
    }

    public void addChild(Person child) {
        this.children.add(child);
    }

    public List<Person> getChildren() {
        return this.children;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
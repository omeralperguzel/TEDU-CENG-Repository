//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 3 Q2
//Description: This is a Java program that allows the user to create new employees and use them in a binary search tree.
//-----------------------------------------------------

package CMPE223SS.HW3.Q2;

class Employee {
    int id;
    String name;
    boolean gender;

    //This is the constructor of the class Employee
    public Employee(int id, String name, boolean gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    //This is the toString method used for printing the employee information
    public String toString() {
        return id + " " + name + " " + (gender ? "Female" : "Male");
    }
}
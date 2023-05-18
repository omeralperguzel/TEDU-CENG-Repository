//-----------------------------------------------------
//Title: Student
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 2 Q1
//Description: This is a Java program that allows the user to create and manage student by using comparable interface.
//-----------------------------------------------------

package CMPE223SS.HW2.Q1.Q1A2;

public class Student implements Comparable<Student> {
    private String name;
    private long id;
    private int semesterNo;

    // Constructor for the student class
    public Student(String name, long id, int semesterNo) {
        this.name = name;
        this.id = id;
        this.semesterNo = semesterNo;
    }

    // It gives the name of the student
    public String getName() {
        return name;
    }

    // It gives the id of the student
    public long getId() {
        return id;
    }

    // It gives the semester number of the student
    public int getSemesterNo() {
        return semesterNo;
    }

    @Override
    // It compares the students by their id, semester number and name
    public int compareTo(Student o) {
        if (this.id != o.id) {
            return Long.compare(this.id, o.id);
        } else if (this.semesterNo != o.semesterNo) {
            return Integer.compare(this.semesterNo, o.semesterNo);
        } else {
            return this.name.compareTo(o.name);
        }
    }
}
//-----------------------------------------------------
//Title: Student
//Author: Ömer Alper Güzel
//ID: 16057474442
//Section: 2
//Assignment: 1 Q1
//Description: This is a Java program used to create a student object, add it to the stack and linkedList, removes the students from the stack and linkedList, summarizes the students in the stack and linkedList, and exits the program.
//-----------------------------------------------------

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Student {

    private int ID, ranking;
    private String Name;
    private double grade1, grade2, grade3, gradeAverage;

    static Scanner input = new Scanner(System.in);
    static Stack<Student> stack = new Stack<>();
	static LinkedList<Student> linkedlist = new LinkedList<>();

    public Student(int ID, String Name, double grade1, double grade2, double grade3, int ranking) {
        this.ID = ID;
        this.Name = Name;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.gradeAverage = grade1 * 0.3 + grade2 * 0.2 + grade3 * 0.5;
        this.ranking = ranking;
    }
//ID is the student's ID, Name is the student's name, grade1, grade2, and grade3 are the student's three grades, and gradeAverage is the student's average grade.
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getGrade1() {
        return grade1;
    }

    public void setGrade1(double grade1) {
        this.grade1 = grade1;
    }   

    public double getGrade2() {
        return grade2;
    }

    public void setGrade2(double grade2) {
        this.grade2 = grade2;
    }

    public double getGrade3() {
        return grade3;
    }

    public void setGrade3(double grade3) {
        this.grade3 = grade3;
    }

    public double getGradeAverage() {
        return gradeAverage;
    }

    public void setGradeAverage(double gradeAverage) {
        this.gradeAverage = gradeAverage;
    }

    public int getRanking() {
        return ranking;
    }
//It is set to 0 by default. The program then reassigns the ranking of each student according to their average grade.
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
//The program uses the get and set methods to access the fields of the Student class.
    public String toString() {
        return Name + "'s ID is " + ID + ". His grades are " + grade1 + ", " + grade2 + " and " + grade3 + ". ";
    }
//The user is prompted to enter the student's ID, name, and three grades. Then the program creates a new Student object according to this information and adds it to the stack and linkedList.

    public void createStudent(){
        System.out.println("Enter the ID of the student.");
	    ID = input.nextInt();
	    input.nextLine();
	    System.out.println("Enter the name of the student.");
	    Name = input.nextLine();
	    System.out.println("Enter the grade1 of the student.");
	    grade1 = input.nextDouble();
	    System.out.println("Enter the grade2 of the student.");
	    grade2 = input.nextDouble();
	    System.out.println("Enter the grade3 of the student.");
	    grade3 = input.nextDouble();
	    Student student = new Student(ID, Name, grade1, grade2, grade3, ranking);
	    stack.push(student);

        int index = 0;
        for (Student s : linkedlist) {
            if (student.getGradeAverage() > s.getGradeAverage()) {
                break;
            }
            index++;
        }
        linkedlist.add(index, student);
    }

//The program removes the top student from the stack and linkedList if the stack is not empty. After that process, it will displays the student's ID, grades, and rank in the class.

    public static void removeStudents(){
        if (stack.isEmpty()) {
            System.out.println("There are no students to remove.");
        } else {
            Student student = stack.pop();
            linkedlist.remove(student);
            int rank = linkedlist.size() + 1;
            System.out.println(student.getName() + "'s ID was " + student.getID() + ". His grades were " + student.getGrade1() + ", " + student.getGrade2() + " and " + student.getGrade3() + ". He was ranked " + student.getRanking() + " in the class.");
        }
    }

//The program iterates over th1is temporary stack and displays each student's information and rank in the class. This can be done by using a temporary Stack<Student> to store the students from the stack in reverse order. Then, the program iterates over the temporary stack and displays each student's information and rank in the class. Finally, the program adds the students back to the stack in the original order.

    public static void summarizeStudents(){
        Stack<Student> tempStack = new Stack<>();
	                while (!stack.empty()) {
	                    Student student = stack.pop();
	                    tempStack.push(student);
                        student.ranking = linkedlist.indexOf(student) + 1;
	                    int rank = linkedlist.indexOf(student) + 1;
	                    System.out.println(student.toString() + "He is ranked " + rank + " in the class.");
	                     
	                }
	                while (!tempStack.empty()) {
	                    stack.push(tempStack.pop());
	                }
    }
//The program exits the loop. Then it will print the information of all the students in the stack according their rank in the class.
    public static void Exit(){
        for (Student s : stack) {
            int rank = linkedlist.indexOf(s) + 1;
            System.out.println(s.toString() + "He is ranked " + rank + " in the class.");
        }
        System.exit(0);
    }
}

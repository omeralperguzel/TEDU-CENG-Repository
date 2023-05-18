//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 1 Q1
//Description: This is a Java program that Student.java and Stack.java are used to create a student object, add it to the stack and linkedList, removes the students from the stack and linkedList, summarizes the students in the stack and linkedList, and exits the program.
//-----------------------------------------------------

public class Main {

	public static void main(String[] args) {

//The program uses two data structures: a Stack<Student> called stack and a LinkedList<Student> called linkedList. These data structures are used to store the students created by the user.
// 1)The program uses the stack to store the students in the order they are created. It uses the push() method of the stack to add a student to the stack.
// 2)The program uses the linkedList to store the students in the order they are removed. It uses the add() method of the linkedList to add a student to the linkedList.

	        while (true) {
            //The program enters an infinite loop where the user can choose different options to manage the students.
        	
	            System.out.println("Choose an option:\n1) Create a student:\n2) Remove a student:\n3) Summarize all students:\n4) Exit:");

                int option = Student.input.nextInt();
	            Student.input.nextLine();

	            if (option == 1) {
                    Student student = new Student(0,"",0,0,0,0);
                    student.createStudent();
	            } 
                else if (option == 2) {
                    Student.removeStudents();
	            } 
                else if (option == 3) {
                    Student.summarizeStudents();
	            } 
                else if (option == 4) {
                    Student.Exit();
	            } 
                else {
                    //If the user does not enter a number from 1 to 4,then this else part will be evaluated.
	                System.out.println("Invalid option.");
	            }
	        }
	 
}

}
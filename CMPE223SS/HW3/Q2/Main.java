//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 3 Q2
//Description: This is a Java program that allows the user to manage a database of employees.
//-----------------------------------------------------

package CMPE223SS.HW3.Q2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDatabase database = new EmployeeDatabase();

        while (true) {
            System.out.println("Enter operation code: ");
            //1-> Insert, 2-> Delete, 3-> search, 4-> List, 5->List with Range, 6-> Quit
            int code = scanner.nextInt();

            switch (code) {
                //This is the case where the user wants to insert a new employee into the database
                case 1:
                    System.out.println("Enter information:");
                    scanner.useDelimiter("\r\n|\s");  
                    //Printing the tokenized Strings  
                    int id = Integer.parseInt(scanner.next()); 
                    String name = (scanner.next()); 
                    String genderString = (scanner.next());
                    boolean gender = false;
                    //That is needed because genders are accepted as strings but need to be stored as booleans
                    if (genderString.compareTo("Male")==0){
                        gender = false;
                    } 
                    else if (genderString.compareTo("Female") == 0){
                        gender = true;
                    }
                    database.insertEmployee(id, name, gender);
                    break;
                //This is the case where the user wants to delete an employee from the database
                case 2:
                    System.out.println("Enter ID to be deleted:");
                    id = scanner.nextInt();
                    database.deleteEmployee(id);
                    break;
                //This is the case where the user wants to search for an employee in the database
                case 3:
                    System.out.println("Enter ID to be searched:");
                    id = scanner.nextInt();
                    database.searchEmployee(id);
                    break;
                //This is the case where the user wants to list all the employees in the database
                case 4:
                    database.listAllEmployees();
                    break;
                //This is the case where the user wants to list all the employees in the database within a range
                case 5:
                    System.out.println("Enter bounds of range:");
                    int minId = scanner.nextInt();
                    int maxId = scanner.nextInt();
                    database.listEmployeesInRange(minId, maxId);
                    break;
                //This is the case where the user wants to quit the program
                case 6:
                    System.out.println("Stopped!");
                    scanner.close();
                    return;
                //This is the case where the user enters an invalid operation code
                default:
                    System.out.println("Invalid operation code.");
                    break;
            }
        }
    }
}
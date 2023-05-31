package CMPE223SS.HW3.Q2.Chegg1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDatabase employeeDatabase = new EmployeeDatabase();

        while (true) {
            System.out.println("Enter operation code:\n1 -> Insert\n2 -> Delete\n3 -> Search\n4 -> List\n5 -> List with Range\n6 -> Quit");
            int code = scanner.nextInt();

            switch (code) {
                case 1:
                    System.out.println("Enter information: ");
                    int id = scanner.nextInt();
                    String name = scanner.next();
                    boolean gender = scanner.nextBoolean();
                    employeeDatabase.insertEmployee(id, name, gender);
                    break;
                case 2:
                    System.out.println("Enter ID to be deleted: ");
                    int deleteId = scanner.nextInt();
                    employeeDatabase.deleteEmployee(deleteId);
                    break;
                case 3:
                    System.out.println("Enter ID to be searched: ");
                    int searchId = scanner.nextInt();
                    employeeDatabase.searchEmployee(searchId);
                    break;
                case 4:
                    employeeDatabase.listAllEmployees();
                    break;
                case 5:
                    System.out.println("Enter bounds of range: ");
                    int minId = scanner.nextInt();
                    int maxId = scanner.nextInt();
                    employeeDatabase.listEmployeesInRange(minId, maxId);
                    break;
                case 6:
                    System.out.println("Stopped!");
                    return;
                default:
                    System.out.println("Invalid operation code!");
                    break;
            }
        }
    }
}
//-----------------------------------------------------
//Title: Library
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 4
//Description: This is a Java program that allows the user to manage a library by taking input from a file, and then prints the customer and book information.
//-----------------------------------------------------

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Library {
    public static void main(String[] args) {
        //This takes the file name from the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter log file name:");
        String fileName = scanner.nextLine();
        scanner.close();

        //Initialize the hash map, priority queue and array list
        Map<String, Book> bookInfo = new HashMap<>();
        PriorityQueue<Customer> waitingCustomers = new PriorityQueue<>();
        List<Integer> days = new ArrayList<>();

        //It reads the file and stores the information in the hash map, priority queue and array list by using both the try-catch and switch-case statements
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	            String line =  "";
	            int section = 0; // 0: Book Info, 1: Day Info, 2: Customer Info

	            while ((line = reader.readLine()) != null) {
	                // If this is equal to "***BOOK INFO**", then we are in the book info section
                    if (line.compareTo("***BOOK INFO**")==0) {
	                    section=0;
	                    continue;
	                }
                    // If this is equal to "**DAY INFO**", then we are in the day info section
	                else if (line.compareTo("**DAY INFO**")==0) {
	                    section=1;
	                    continue;
	                }
                    // If this is equal to "***CUSTOMER INFO***", then we are in the customer info section
	                else if (line.compareTo("***CUSTOMER INFO***")==0) {
	                    section=2;
	                    continue;
	                }

	                switch (section) {
                        //This is the case where we are in the book info section
                        case 0:
                        String[] bookInfoArr = line.split(",");
	                    if (bookInfoArr.length != 3) {
	                        System.out.println("Invalid book info: " + line);
	                        continue;
	                    }
	                    String author = bookInfoArr[0];
	                    String name = bookInfoArr[1];
	                    int count = Integer.parseInt(bookInfoArr[2]);
                        Book book = new Book(author, name, count);
                        bookInfo.put(name, book);
	                    break;
                        // This is the case where we are in the day info section
                        case 1:
	                    int day = Integer.parseInt(line);
	                    days.add(day);
	                    break;
                        // This is the case where we are in the customer info section
                        case 2:
	                    String[] customerInfo = line.split(",");
	                    // If the length of the customer info is not equal to 5, then it is an invalid customer info
                        if (customerInfo.length != 5) {
                            continue;
	                    }
                        // This part is used to store the customer information in the priority queue
	                    int registrationYear = Integer.parseInt(customerInfo[0]);
	                    String customerID = customerInfo[1];
	                    int startReservationDate = Integer.parseInt(customerInfo[2]);
	                    int totalReservationDays = Integer.parseInt(customerInfo[3]);
	                    String desiredBook = customerInfo[4];
                        Customer customer = new Customer(registrationYear, customerID, startReservationDate, totalReservationDays, desiredBook);
                        waitingCustomers.add(customer);
                        break;
                        // This is the default case where the section is invalid
                        default:
	                    System.out.println("Invalid section: " + section);
	                    break;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }
       
        // This part is needed to traverse the days array list and print the day information.
        for (int day : days) {
            System.out.println("Day " + day + ":");


            // This part is used to print the customer information
            System.out.println("Customer info:");
            boolean hasWaitingCustomers = false;
            PriorityQueue<Customer> tempQueue = new PriorityQueue<>(waitingCustomers);
            // This while loop is used to print the customers who are waiting for the book
            while (!tempQueue.isEmpty()) {
                Customer customer = tempQueue.poll();
                if (customer.getReservationStartDay() + customer.getTotalReservationDay() == day) {
                    System.out.println(customer.getCustomerId() + " waits " + customer.getBookName() + " since day " + customer.getReservationStartDay() + ".");
                    hasWaitingCustomers = true;
                }
            }
            // This for loop is used to print the customers who are not waiting for the book
            for (Map.Entry<String, Book> entry : bookInfo.entrySet()) {
                Book book = entry.getValue();
                String bookName = entry.getKey();
                int numCopies = book.getNumCopies();
                if (numCopies == 0) {
                    // This is a priority queue that is used to store the customers who are not waiting for the book
                    PriorityQueue<Customer> tempQueue2 = new PriorityQueue<>(waitingCustomers);
                    while (!tempQueue2.isEmpty()) {
                        // This customer object is created for using the poll method
                        Customer customer = tempQueue2.poll();
                        if (customer.getBookName().equals(bookName)) {
                            System.out.println(customer.getCustomerId() + " waits " + customer.getBookName() + " since day " + customer.getReservationStartDay() + ".");
                            break;
                        }
                    }
                }
            
            }
            // This if statement is used to print "No waiting customer" if there is no waiting customer in the queue
            if (!hasWaitingCustomers) {
                System.out.println("No waiting customer");
            }

            // This part is used to print the book information
            System.out.println("Book info:");
            for (Map.Entry<String, Book> entry : bookInfo.entrySet()) {
                String bookName = entry.getKey();
                Book book = entry.getValue();
                int numCopies = book.getNumCopies();
                System.out.println(book.getAuthor() + "," + bookName + "," + numCopies);                
            }

            // This part is used to update and remove the customers who got the book from the queue and update the book information accordingly.
            for (Map.Entry<String, Book> entry : bookInfo.entrySet()) {
                String bookName = entry.getKey();
                Book book = entry.getValue();
                int numCopies = book.getNumCopies();
                PriorityQueue<Customer> tempQueue3 = new PriorityQueue<>(waitingCustomers);
                while (!tempQueue3.isEmpty()) {
                    Customer customer = tempQueue3.poll();
                    if (customer.getBookName().equals(bookName)) {
                        if (numCopies > 0) {
                            book.setNumCopies(numCopies - 1);
                            waitingCustomers.remove(customer);
                            break;
                        }
                    }
                }
            }
        }
    }
}
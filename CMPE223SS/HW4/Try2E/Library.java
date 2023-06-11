package Try2E;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//TRY2C WITH NEW INPUT TAKER. IT LOOKS LIKE IT CAN WORK BUT IT DOES NOT WORK AS EXPECTED.
public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter log file name:");
        String fileName = scanner.nextLine();

        Map<String, Book> bookInfo = new HashMap<>();
        PriorityQueue<Customer> waitingCustomers = new PriorityQueue<>();
        List<Integer> days = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/omer/TEDU-CENG-Repository/CMPE223SS/HW4/Try2/log1.txt"))) {
	            String line =  "";
	            int section = 0; // 0: Book Info, 1: Day Info, 2: Customer Info

	            while ((line = reader.readLine()) != null) {
	                if (line.compareTo("***BOOK INFO**")==0) {
	                    section=0;
	                    continue;
	                }
	                else if (line.compareTo("**DAY INFO**")==0) {
	                    section=1;
	                    continue;
	                }
	                else if (line.compareTo("***CUSTOMER INFO***")==0) {
	                    section=2;
	                    continue;
	                }

	                switch (section) {
	                case 0:
                    String[] bookInfoArr = line.split(",");
	                    if (bookInfoArr.length != 3) {
	                        System.out.println("Invalid book info: " + line);
	                        continue;
	                    }
	                    String writer = bookInfoArr[0];
	                    String name = bookInfoArr[1];
	                    int count = Integer.parseInt(bookInfoArr[2]);
                        Book book = new Book(writer, name, count);
                        bookInfo.put(name, book);
	                    break;
	                case 1:
	                    int day = Integer.parseInt(line);
	                    days.add(day);
	                    break;
	                case 2:
	                    String[] customerInfo = line.split(",");
	                    if (customerInfo.length != 5) {
                            continue;
	                    }
	                    int registrationYear = Integer.parseInt(customerInfo[0]);
	                    String customerID = customerInfo[1];
	                    int startReservationDate = Integer.parseInt(customerInfo[2]);
	                    int totalReservationDays = Integer.parseInt(customerInfo[3]);
	                    String desiredBook = customerInfo[4];
                        Customer customer = new Customer(registrationYear, customerID, startReservationDate, totalReservationDays, desiredBook);
                        waitingCustomers.add(customer);
                        break;
	                default:
	                    System.out.println("Invalid section: " + section);
	                    break;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }
        
        for (int day : days) {
            System.out.println("Day " + day + ":");


            // Print waiting customers
            System.out.println("Customer info:");
            boolean hasWaitingCustomers = false;
            boolean hasWaitingCustomers2 = false;
            PriorityQueue<Customer> tempQueue = new PriorityQueue<>(waitingCustomers);
            while (!tempQueue.isEmpty()) {
                Customer customer = tempQueue.poll();
                if (customer.getReservationStartDay() + customer.getTotalReservationDay() == day) {
                    System.out.println(customer.getCustomerId() + " waits " + customer.getBookName() + " since day " + customer.getReservationStartDay() + ".");
                    hasWaitingCustomers = true;
                }
            }
            if (!hasWaitingCustomers) {
                System.out.println("No waiting customer");
            }

            // Print book info
            System.out.println("Book info:");
            for (Map.Entry<String, Book> entry : bookInfo.entrySet()) {
                String bookName = entry.getKey();
                Book book = entry.getValue();
                int numCopies = book.getNumCopies();
                System.out.println(book.getAuthor() + "," + bookName + "," + numCopies);
                if (numCopies == 0) {
                    PriorityQueue<Customer> tempQueue2 = new PriorityQueue<>(waitingCustomers);
                    while (!tempQueue2.isEmpty()) {
                        Customer customer = tempQueue2.poll();
                        if (customer.getBookName().equals(bookName)) {
                            System.out.println(customer.getCustomerId() + " waits " + customer.getBookName() + " since day " + customer.getReservationStartDay() + ".");
                            break;
                        }
                    }
                }
                
            }

            // Update book info and waiting customers
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

class Book {
    private String author;
    private String name;
    private int numCopies;

    public Book(String author, String name, int numCopies) {
        this.author = author;
        this.name = name;
        this.numCopies = numCopies;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }
}

class Customer implements Comparable<Customer> {
    private int registrationYear;
    private String customerId;
    private int reservationStartDay;
    private int totalReservationDay;
    private String bookName;

    public Customer(int registrationYear, String customerId, int reservationStartDay, int totalReservationDay, String bookName) {
        this.registrationYear = registrationYear;
        this.customerId = customerId;
        this.reservationStartDay = reservationStartDay;
        this.totalReservationDay = totalReservationDay;
        this.bookName = bookName;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getReservationStartDay() {
        return reservationStartDay;
    }

    public int getTotalReservationDay() {
        return totalReservationDay;
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public int compareTo(Customer other) {
        if (this.registrationYear != other.registrationYear) {
            return Integer.compare(this.registrationYear, other.registrationYear);
        } else if (this.reservationStartDay != other.reservationStartDay) {
            return Integer.compare(this.reservationStartDay, other.reservationStartDay);
        } else {
            return this.customerId.compareTo(other.customerId);
        }
    }
}
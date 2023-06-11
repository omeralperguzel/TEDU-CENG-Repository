package Try4;


import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner; 

class Customer implements Comparable<Customer>{ 
    private int registrationYear;
    private String customerID;
    private int startReservationDate;
    private int totalReservationDay;
    private String desiredBook;
    
    public Customer(int registrationYear, String customerID, int startReservationDate, int totalReservationDay, String desiredBook) {
        this.registrationYear = registrationYear;
        this.customerID = customerID;
        this.startReservationDate = startReservationDate;
        this.totalReservationDay = totalReservationDay;
        this.desiredBook = desiredBook;
    }
    public int getRegistrationYear() {
        return registrationYear;
    }
    public String getCustomerID() {
        return customerID;
    }
    public int getStartReservationDate() {
        return startReservationDate;
    }
    public int getTotalReservationDay() {
        return totalReservationDay;
    }
    public String getDesiredBook() {
        return desiredBook;
}
    @Override
    public int compareTo(Customer other) {
        if (this.registrationYear != other.registrationYear) {
            return Integer.compare(this.registrationYear, other.registrationYear);
        } 
        else if (this.startReservationDate != other.startReservationDate) {
            return Integer.compare(this.startReservationDate, other.startReservationDate);
        } 
        else {
            return this.customerID.compareTo(other.customerID);
        }
    } 
}

class Book {
    private String writer;
    private String name;
    private int quantity;
    
    public Book(String writer, String name, int quantity) {
        this.writer = writer;
        this.name = name;
        this.quantity = quantity;
    }
    public String getWriter() {
        return writer;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    } 
}

public class LibraryManager {
    private PriorityQueue <Customer> waitingCustomers;
    private HashMap <String, Book> books;
    
    public LibraryManager() {
        waitingCustomers = new PriorityQueue<>();
        books = new HashMap<>();
    }
    public void processLogFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("***BOOK INFO")) {
                    processBookInfo(scanner.nextLine());
                }
                else if (line.startsWith("**DAY INFO")) {
                    processDayInfo(scanner.nextLine());
                }
                else if (line.startsWith("***CUSTOMER INFO")) {
                    processCustomerInfo(scanner);
                }
            }
                scanner.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("File not found: " + fileName);
            }
        }

private void processBookInfo(String line) {
        String[] booksData = line.split(", ");
        for (String bookData : booksData) {
            String[] bookInfo = bookData.split(", ");
            String writer = bookInfo[0];
            String name = bookInfo[1];
            int quantity = Integer.parseInt(bookInfo[2]);
            Book book = new Book(writer, name, quantity);
            books.put(name, book);
        }
    }

private void processDayInfo(String line) {
        int day = Integer.parseInt(line);
        System.out.println("Day " + day + ":");

        System.out.println("Customer info:");
        List<Customer> processedCustomers = new ArrayList<>();
        while (!waitingCustomers.isEmpty() && waitingCustomers.peek().getStartReservationDate() <= day) {
            Customer customer = waitingCustomers.poll();
            int reservationEndDay = customer.getStartReservationDate() + customer.getTotalReservationDay();
            if (reservationEndDay > day) {
                System.out.println(customer.getCustomerID() + " waits " + customer.getDesiredBook() + " since day " + customer.getStartReservationDate() + ".");
                processedCustomers.add(customer);
            }
        }
        waitingCustomers.addAll(processedCustomers);

        System.out.println("Book info:");
        for (Map.Entry<String, Book>entry : books.entrySet()) {
            Book book = entry.getValue();
            System.out.println(book.getWriter() + ", " + book.getName() + "," + book.getQuantity());
        }
        System.out.println();
    }

private void processCustomerInfo(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("No waiting customer")) {
                System.out.println(line);
                break;
            }

            String[] customerInfo = line.split(", ");
            int registrationYear = Integer.parseInt(customerInfo[0]);
            String customerID = customerInfo[1];
            int startReservationDate = Integer.parseInt(customerInfo[2]);
            int totalReservationDay = Integer.parseInt(customerInfo[3]);
            String desiredBook = customerInfo[4];
            Customer customer = new Customer(registrationYear, customerID, startReservationDate, totalReservationDay, desiredBook);
            waitingCustomers.add(customer);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter log file name:");
        String fileName = scanner.nextLine();
        scanner.close();

        LibraryManager libraryManager = new LibraryManager();
        libraryManager.processLogFile(fileName);
    }
}
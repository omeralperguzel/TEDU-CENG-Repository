package Try3C;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

//REWRITTEN VERSION OF TRY2C BY GITHUB COPILOT. IT ALSO CONTAINS SOME STUFF FROM TRY2C CLASSES BUT IT ONLY PRINTS "BOOK NOT FOUND:" ERROR
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter log file name: ");
        String fileName = scanner.nextLine();
        PriorityQueue<Customer> waitingCustomers = new PriorityQueue<>();
        Map<String, Book> bookInfo = new HashMap<>();
        List<Integer> days = new ArrayList<>();
        try {
            parseLogFile(fileName, bookInfo);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return;
        }
        System.out.println("Book information:");
        for (Book book : bookInfo.values()) {
            System.out.println(book.getAuthor() + ", " + book.getName() + ", " + book.getNumCopies());
        }
        for (int day : days) {
            System.out.println("Day " + day + ":");

            // Print waiting customers
            boolean hasWaitingCustomers = false;
            PriorityQueue<Customer> tempQueue = new PriorityQueue<>(waitingCustomers);
            while (!tempQueue.isEmpty()) {
                Customer customer = tempQueue.poll();
                if (customer.getReservationStartDay() + customer.getTotalReservationDay() == day) {
                    System.out.println("C" + customer.getCustomerId() + " waits " + customer.getBookName() + " since day " + customer.getReservationStartDay() + ".");
                    hasWaitingCustomers = true;
                }
            }
            if (!hasWaitingCustomers) {
                System.out.println("No waiting customer");
            }

            // Print book info
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
                            System.out.println("C" + customer.getCustomerId() + " waits " + customer.getBookName() + " since day " + customer.getReservationStartDay() + ".");
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

    private static void parseLogFile(String fileName, Map<String, Book> bookInfo) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String section = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("***")) {
                section = line.substring(3, line.length() - 3);
            } else if (section.equals("BOOK INFO")) {
                parseBookInfo(line, bookInfo);
            } else if (section.equals("CUSTOMER INFO")) {
                parseCustomerInfo(line, bookInfo);
            }
        }
        scanner.close();
    }

    private static void parseBookInfo(String line, Map<String, Book> bookInfo) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            System.out.println("Invalid book info: " + line);
            return;
        }
        String author = parts[0];
        String bookName = parts[1];
        int numCopies = Integer.parseInt(parts[2]);
        Book book = new Book(author, bookName, numCopies);
        bookInfo.put(bookName, book);
    }

    private static void parseCustomerInfo(String line, Map<String, Book> bookInfo) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            System.out.println("Invalid customer info: " + line);
            return;
        }
        int year = Integer.parseInt(parts[0]);
        String customerId = parts[1];
        int day = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[3]);
        String bookName = parts[4];
        Book book = bookInfo.get(bookName);
        if (book == null) {
            System.out.println("Book not found: " + bookName);
            return;
        }
        book.checkOut();
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

    public void checkOut() {
        if (numCopies > 0) {
            numCopies--;
        }
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
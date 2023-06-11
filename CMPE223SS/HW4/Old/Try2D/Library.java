package Try2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter log file name:");
        String fileName = scanner.nextLine();

        Map<String, Book> bookInfo = new HashMap<>();
        Map<Integer, List<Customer>> waitingCustomers = new HashMap<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            String section = "";
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.startsWith("***")) {
                    section = line.substring(3).trim();
                } else if (!line.isEmpty()) {
                    switch (section) {
                        case "BOOK INFO":
                            parseBookInfo(line, bookInfo);
                            break;
                        case "DAY INFO":
                            int day = Integer.parseInt(line);
                            waitingCustomers.put(day, new ArrayList<>());
                            break;
                        case "CUSTOMER INFO":
                            parseCustomerInfo(line, waitingCustomers);
                            break;
                        default:
                            System.out.println("Invalid section: " + section);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return;
        }

        for (Map.Entry<Integer, List<Customer>> entry : waitingCustomers.entrySet()) {
            int day = entry.getKey();
            List<Customer> customers = entry.getValue();

            System.out.println("Day " + day + ":");

            if (customers.isEmpty()) {
                System.out.println("No waiting customer");
            } else {
                for (Customer customer : customers) {
                    System.out.println("C" + customer.getCustomerId() + " waits " + customer.getBookName() + " since day " + customer.getReservationStartDay() + ".");
                }
            }

            for (Map.Entry<String, Book> bookEntry : bookInfo.entrySet()) {
                String bookName = bookEntry.getKey();
                Book book = bookEntry.getValue();

                System.out.println(book.getAuthor() + "," + bookName + "," + book.getNumCopies());
            }

            for (Customer customer : customers) {
                String bookName = customer.getBookName();
                Book book = bookInfo.get(bookName);

                if (book.getNumCopies() > 0) {
                    book.setNumCopies(book.getNumCopies() - 1);
                    System.out.println("C" + customer.getCustomerId() + " gets " + bookName + ".");
                }
            }
        }
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
        Book book = new Book(author, numCopies);
        bookInfo.put(bookName, book);
    }

    private static void parseCustomerInfo(String line, Map<Integer, List<Customer>> waitingCustomers) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            System.out.println("Invalid customer info: " + line);
            return;
        }
        int registrationYear = Integer.parseInt(parts[0]);
        String customerId = parts[1];
        int reservationStartDay = Integer.parseInt(parts[2]);
        int totalReservationDay = Integer.parseInt(parts[3]);
        String bookName = parts[4];
        Customer customer = new Customer(registrationYear, customerId, reservationStartDay, totalReservationDay, bookName);
        for (Map.Entry<Integer, List<Customer>> entry : waitingCustomers.entrySet()) {
            int day = entry.getKey();
            if (reservationStartDay <= day && day < reservationStartDay + totalReservationDay) {
                entry.getValue().add(customer);
            }
        }
    }
}

class Book {
    private String author;
    private int numCopies;

    public Book(String author, int numCopies) {
        this.author = author;
        this.numCopies = numCopies;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }
}

class Customer {
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
}

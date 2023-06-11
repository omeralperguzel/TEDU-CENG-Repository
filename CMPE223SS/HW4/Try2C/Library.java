package Try2C;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//IT PRINTS "INVALID BOOK INFO: 14". I THINK IT HAS ISSUES WITH THE PARSING OF DAYS AND CUSTOMERS. I TRIED TO FIX IT BUT I COULDN'T.
public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter log file name:");
        String fileName = scanner.nextLine();

        Map<String, Book> bookInfo = new HashMap<>();
        PriorityQueue<Customer> waitingCustomers = new PriorityQueue<>();
        List<Integer> days = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            String section = "";
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.startsWith("***")) {
                    section = line.substring(3, line.length() - 2);
                } else if (!line.isEmpty()) {
                    switch (section) {
                        case "BOOK INFO":
                            parseBookInfo(line, bookInfo);
                            break;
                        case "DAY INFO":
                            int day = Integer.parseInt(line);
                            days.add(day);
                            break;
                        case "CUSTOMER INFO":
                            String[] customerData = line.split(",");
                            int registrationYear = Integer.parseInt(customerData[0].trim());
                            String customerId = customerData[1].trim();
                            int reservationStartDay = Integer.parseInt(customerData[2].trim());
                            int totalReservationDay = Integer.parseInt(customerData[3].trim());
                            String bookName = customerData[4].trim();
                            Customer customer = new Customer(registrationYear, customerId, reservationStartDay, totalReservationDay, bookName);
                            waitingCustomers.add(customer);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return;
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
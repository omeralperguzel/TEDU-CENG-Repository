package Try3B;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


//MOSTLY USELESS CODE. LOOKS FINE BUT PRINTS ILLEGAL ARGUMENT EXCEPTION
class Customer {
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

public class LibraryManagementSystem {
    private static final int MAX_CUSTOMERS = 200;

    private static PriorityQueue<Customer> customerQueue;
    private static Map<String, Book> books;

    public static void main(String[] args) {
        String fileName = getInput("Enter log file name: ");
        parseLogFile(fileName);
        processDays();
    }

    private static void parseLogFile(String fileName) {
        customerQueue = new PriorityQueue<>(MAX_CUSTOMERS, Comparator
                .comparing(Customer::getRegistrationYear)
                .thenComparing(Customer::getStartReservationDate));

        books = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int section = 0;

            while ((line = br.readLine()) != null) {
                if (line.equals("***BOOK INFO**")) {
                    section = 1;
                } else if (line.equals("**DAY INFO**")) {
                    section = 2;
                } else if (line.equals("***CUSTOMER INFO***")) {
                    section = 3;
                } else {
                    switch (section) {
                        case 1:
                            String[] bookInfo = line.split(",");
                            String writer = bookInfo[0];
                            String name = bookInfo[1];
                            int quantity = Integer.parseInt(bookInfo[2]);
                            books.put(name, new Book(writer, name, quantity));
                            break;
                        case 2:
                            int day = Integer.parseInt(line);
                            processDay(day);
                            break;
                        case 3:
                            String[] customerInfo = line.split(",");
                            int registrationYear = Integer.parseInt(customerInfo[0]);
                            String customerID = customerInfo[1];
                            int startReservationDate = Integer.parseInt(customerInfo[2]);
                            int totalReservationDay = Integer.parseInt(customerInfo[3]);
                            String desiredBook = customerInfo[4];
                            customerQueue.offer(new Customer(registrationYear, customerID, startReservationDate, totalReservationDay, desiredBook));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processDays() {
        while (!customerQueue.isEmpty()) {
            Customer customer = customerQueue.poll();
            int reservationEndDay = customer.getStartReservationDate() + customer.getTotalReservationDay();

            if (reservationEndDay > getCurrentDay()) {
                customerQueue.offer(customer);
                break;
            }

            System.out.println("Day " + reservationEndDay + ":");
            System.out.println("Customer info:");

            boolean waitingCustomers = false;
            for (Customer waitingCustomer : customerQueue) {
                if (waitingCustomer.getStartReservationDate() <= getCurrentDay()) {
                    waitingCustomers = true;
                    System.out.println(waitingCustomer.getCustomerID() + " waits " + waitingCustomer.getDesiredBook() + " since day " + waitingCustomer.getStartReservationDate());
                } else {
                    break;
                }
            }

            if (!waitingCustomers) {
                System.out.println("No waiting customer");
            }

            System.out.println("Book info:");
            for (Book book : books.values()) {
                System.out.println(book.getWriter() + "," + book.getName() + "," + book.getQuantity());
            }

            System.out.println();

            Book desiredBook = books.get(customer.getDesiredBook());
            if (desiredBook != null && desiredBook.getQuantity() > 0) {
                desiredBook.setQuantity(desiredBook.getQuantity() - 1);
            }
        }
    }

    private static int getCurrentDay() {
        // Simulating current day
        return 14;
    }

    private static void processDay(int day) {
        while (!customerQueue.isEmpty() && customerQueue.peek().getStartReservationDate() == day) {
            Customer customer = customerQueue.poll();
            System.out.println("Day " + day + ":");
            System.out.println("Customer info:");
            System.out.println(customer.getCustomerID() + " waits " + customer.getDesiredBook() + " since day " + customer.getStartReservationDate());
            System.out.println("Book info:");
            for (Book book : books.values()) {
                System.out.println(book.getWriter() + "," + book.getName() + "," + book.getQuantity());
            }
            System.out.println();
        }
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        try {
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

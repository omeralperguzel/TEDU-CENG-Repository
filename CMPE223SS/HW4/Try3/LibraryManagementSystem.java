package Try3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


//CHATGPT'S FIRST PROPER TAKE
public class LibraryManagementSystem {
    public static void main(String[] args) {
        String logFileName = getInput("Enter log file name: ");
        PriorityQueue<Customer> waitingCustomers = new PriorityQueue<>(new CustomerComparator());
        Map<String, Book> books = new HashMap<>();

        try {
            parseLogFile(logFileName, waitingCustomers, books);
        } catch (IOException e) {
            System.out.println("Error reading the log file.");
            return;
        }

        int currentDay = -1;
        while (!waitingCustomers.isEmpty() || currentDay < 30) {
            currentDay++;

            System.out.println("Day " + currentDay + ":");
            System.out.println("Customer info:");
            printWaitingCustomers(waitingCustomers, currentDay);
            System.out.println("Book info:");
            printBooks(books);

            if (currentDay < 30) {
                processDeliveries(waitingCustomers, books, currentDay);
            }
        }
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private static void parseLogFile(String logFileName, PriorityQueue<Customer> waitingCustomers, Map<String, Book> books) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(logFileName));
        String line;

        // Parse book info
        while ((line = reader.readLine()) != null && !line.startsWith("**DAY INFO**")) {
            if (line.startsWith("***BOOK INFO**")) {
                while ((line = reader.readLine()) != null && !line.startsWith("**DAY INFO**")) {
                    String[] bookData = line.split(",");
                    String writer = bookData[0];
                    String name = bookData[1];
                    int count = Integer.parseInt(bookData[2]);
                    books.put(name, new Book(writer, name, count));
                }
            }
        }

        // Parse day info
        while ((line = reader.readLine()) != null && !line.startsWith("***CUSTOMER INFO***")) {
            if (!line.startsWith("**DAY INFO**")) {
                int day = Integer.parseInt(line);
                if (day >= 0 && day <= 30) {
                    while ((line = reader.readLine()) != null && !line.startsWith("***CUSTOMER INFO***")) {
                        if (!line.startsWith("**DAY INFO**")) {
                            int registrationYear = Integer.parseInt(line.split(",")[0]);
                            Customer customer = parseCustomerInfo(reader.readLine(), registrationYear);
                            waitingCustomers.add(customer);
                        }
                    }
                }
            }
        }
    }

    private static Customer parseCustomerInfo(String line, int registrationYear) {
        String[] customerData = line.split(",");
        String customerID = customerData[0];
        int reservationStart = Integer.parseInt(customerData[1]);
        int reservationDays = Integer.parseInt(customerData[2]);
        String desiredBook = customerData[3];
        return new Customer(registrationYear, customerID, reservationStart, reservationDays, desiredBook);
    }

    private static void printWaitingCustomers(PriorityQueue<Customer> waitingCustomers, int currentDay) {
        PriorityQueue<Customer> tempQueue = new PriorityQueue<>(waitingCustomers);
        boolean noWaitingCustomer = true;

        while (!tempQueue.isEmpty()) {
            Customer customer = tempQueue.poll();
            if (customer.getReservationStart() <= currentDay) {
                System.out.println(customer.getCustomerID() + " waits " + customer.getDesiredBook() +
                        " since day " + customer.getReservationStart() + ".");
                noWaitingCustomer = false;
            }
        }

        if (noWaitingCustomer) {
            System.out.println("No waiting customer");
        }
    }

    private static void printBooks(Map<String, Book> books) {
        for (Book book : books.values()) {
            System.out.println(book.getWriter() + "," + book.getName() + "," + book.getCount());
        }
    }

    private static void processDeliveries(PriorityQueue<Customer> waitingCustomers, Map<String, Book> books, int currentDay) {
        PriorityQueue<Customer> tempQueue = new PriorityQueue<>(waitingCustomers);
        while (!tempQueue.isEmpty()) {
            Customer customer = tempQueue.poll();
            if (customer.getReservationStart() == currentDay) {
                Book book = books.get(customer.getDesiredBook());
                if (book != null && book.getCount() > 0) {
                    book.decrementCount();
                    waitingCustomers.remove(customer);
                }
            }
        }
    }
}
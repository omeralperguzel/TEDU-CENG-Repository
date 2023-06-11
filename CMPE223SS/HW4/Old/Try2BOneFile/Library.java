package Try2BOneFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter log file name:");
        String fileName = scanner.nextLine();

        Map<String, Integer> bookInfo = new HashMap<>();
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
                            String[] bookData = line.split(",");
                            String bookName = bookData[1];
                            int numCopies = Integer.parseInt(bookData[2]);
                            bookInfo.put(bookName, numCopies);
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
                             bookName = customerData[4].trim();
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
            for (Map.Entry<String, Integer> entry : bookInfo.entrySet()) {
                String bookName = entry.getKey();
                int numCopies = entry.getValue();
                System.out.println(bookName + "," + numCopies);
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
            for (Map.Entry<String, Integer> entry : bookInfo.entrySet()) {
                String bookName = entry.getKey();
                int numCopies = entry.getValue();
                PriorityQueue<Customer> tempQueue3 = new PriorityQueue<>(waitingCustomers);
                while (!tempQueue3.isEmpty()) {
                    Customer customer = tempQueue3.poll();
                    if (customer.getBookName().equals(bookName)) {
                        if (numCopies > 0) {
                            entry.setValue(numCopies - 1);
                            waitingCustomers.remove(customer);
                            break;
                        }
                    }
                }
            }
        }
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
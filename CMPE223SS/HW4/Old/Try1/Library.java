package Try1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;


//FIRST TAKE OF GITHUB COPILOT, IT PRINTS OUT OF BOUNDS EXCEPTION. OTHER THAN THAT I THINK IT UNDERSTANDS THE BASICS OF THIS QUESTION
public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter log file name:");
        String fileName = scanner.nextLine();
        scanner.close();

        Map<String, Integer> bookMap = new HashMap<>();
        PriorityQueue<Customer> waitingCustomers = new PriorityQueue<>(Comparator.comparing(Customer::getRegistrationYear).thenComparing(Customer::getCustomerId).thenComparing(Customer::getReservationStartDay));
        List<Integer> days = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.startsWith("***BOOK INFO**")) {
                    while (fileScanner.hasNextLine()) {
                        line = fileScanner.nextLine();
                        if (line.startsWith("**DAY INFO**")) {
                            break;
                        }
                        String[] bookInfo = line.split(", ");
                        bookMap.put(bookInfo[1], Integer.parseInt(bookInfo[2]));
                    }
                } else if (line.startsWith("**DAY INFO**")) {
                    while (fileScanner.hasNextLine()) {
                        line = fileScanner.nextLine();
                        if (line.startsWith("***CUSTOMER INFO***")) {
                            break;
                        }
                        days.add(Integer.parseInt(line));
                    }
                } else if (line.startsWith("***CUSTOMER INFO***")) {
                    while (fileScanner.hasNextLine()) {
                        line = fileScanner.nextLine();
                        String[] customerInfo = line.split(", ");
                        int registrationYear = Integer.parseInt(customerInfo[0]);
                        String customerId = customerInfo[1];
                        int reservationStartDay = Integer.parseInt(customerInfo[2]);
                        int totalReservationDay = Integer.parseInt(customerInfo[3]);
                        String bookName = customerInfo[4];

                        if (bookMap.containsKey(bookName) && bookMap.get(bookName) > 0) {
                            bookMap.put(bookName, bookMap.get(bookName) - 1);
                        } else {
                            waitingCustomers.add(new Customer(registrationYear, customerId, reservationStartDay, totalReservationDay, bookName));
                        }
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        for (int day : days) {
            System.out.println("Day " + day + ":");

            // Print waiting customers
            System.out.println("Customer info:");
            List<Customer> customersToPrint = new ArrayList<>();
            while (!waitingCustomers.isEmpty() && waitingCustomers.peek().getReservationStartDay() <= day) {
                customersToPrint.add(waitingCustomers.poll());
            }
            if (customersToPrint.isEmpty()) {
                System.out.println("No waiting customer");
            } else {
                for (Customer customer : customersToPrint) {
                    System.out.println(customer.getCustomerId() + " waits " + customer.getBookName() + " since day " + customer.getReservationStartDay() + ".");
                }
            }

            // Print book info
            System.out.println("Book info:");
            for (Map.Entry<String, Integer> entry : bookMap.entrySet()) {
                System.out.println(entry.getKey() + "," + entry.getValue());
            }
        }
    }
}
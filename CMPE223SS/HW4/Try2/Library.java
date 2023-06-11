package Try2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//SECOND TAKE OF COPILOT, NO MAJOR CHANGES, STILL PRINTS OUT OF BOUNDS EXCEPTION
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
                String[] data = line.split(",");
                switch (section) {
                    case "BOOK INFO":
                        if (data.length == 3) {
                            String bookName = data[1].trim();
                            //String authorName = data[2].trim();
                            int numCopies = Integer.parseInt(data[3].trim());
                            bookInfo.put(bookName, numCopies);
                        } else {
                            System.err.println("Invalid book info: " + line);
                        }
                        break;
                    case "DAY INFO":
                        if (data.length == 1) {
                            int day = Integer.parseInt(data[0].trim());
                            days.add(day);
                        } else {
                            System.err.println("Invalid day info: " + line);
                        }
                        break;
                    case "CUSTOMER INFO":
                        if (data.length == 5) {
                            int customerId = Integer.parseInt(data[0].trim());
                            String customerName = data[1].trim();
                            String bookName = data[2].trim();
                            int reservationStartDay = Integer.parseInt(data[3].trim());
                            int numDays = Integer.parseInt(data[4].trim());
                            waitingCustomers.add(new Customer(customerId, customerName, numDays, reservationStartDay, bookName));
                        } else {
                            System.err.println("Invalid customer info: " + line);
                        }
                        break;
                    default:
                        System.err.println("Invalid section: " + section);
                        break;
                }
            }
        }
    } catch (FileNotFoundException e) {
        System.err.println("File not found: " + fileName);
        return;
    } catch (NumberFormatException e) {
        System.err.println("Invalid number format: " + e.getMessage());
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
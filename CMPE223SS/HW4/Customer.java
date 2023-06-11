//-----------------------------------------------------
//Title: Customer
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 4
//Description: This is a Java program that allows the user to manage customers by getting and setting information about the customers.
//-----------------------------------------------------

class Customer implements Comparable<Customer> {
    // This is the declaration of the variables that will be used in this program.
    private int registrationYear;
    private String customerId;
    private int reservationStartDay;
    private int totalReservationDay;
    private String bookName;

    // This is the constructor of the Customer class.
    public Customer(int registrationYear, String customerId, int reservationStartDay, int totalReservationDay, String bookName) {
        this.registrationYear = registrationYear;
        this.customerId = customerId;
        this.reservationStartDay = reservationStartDay;
        this.totalReservationDay = totalReservationDay;
        this.bookName = bookName;
    }

    // This is the getter method for the registration year.
    public int getRegistrationYear() {
        return registrationYear;
    }

    // This is the getter method for the customer ID.
    public String getCustomerId() {
        return customerId;
    }

    // This is the getter method for the reservation start day.
    public int getReservationStartDay() {
        return reservationStartDay;
    }

    // This is the getter method for the total reservation day.
    public int getTotalReservationDay() {
        return totalReservationDay;
    }

    // This is the getter method for the book name.
    public String getBookName() {
        return bookName;
    }

    // This is the method that is used for comparing the customers according to the registration year, reservation start day and customer ID.
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
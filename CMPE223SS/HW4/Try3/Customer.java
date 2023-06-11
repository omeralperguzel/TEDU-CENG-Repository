package Try3;

class Customer {
    private int registrationYear;
    private String customerID;
    private int reservationStart;
    private int reservationDays;
    private String desiredBook;

    public Customer(int registrationYear, String customerID, int reservationStart, int reservationDays, String desiredBook) {
        this.registrationYear = registrationYear;
        this.customerID = customerID;
        this.reservationStart = reservationStart;
        this.reservationDays = reservationDays;
        this.desiredBook = desiredBook;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public String getCustomerID() {
        return customerID;
    }

    public int getReservationStart() {
        return reservationStart;
    }

    public int getReservationDays() {
        return reservationDays;
    }

    public String getDesiredBook() {
        return desiredBook;
    }
}

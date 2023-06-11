package Try1;
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
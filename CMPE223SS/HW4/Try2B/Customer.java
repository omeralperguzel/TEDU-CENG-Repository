package Try2B;
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
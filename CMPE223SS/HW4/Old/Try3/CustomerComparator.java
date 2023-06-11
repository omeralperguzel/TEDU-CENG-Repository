package Try3;

import java.util.Comparator;

class CustomerComparator implements Comparator<Customer> {
    public int compare(Customer c1, Customer c2) {
        if (c1.getRegistrationYear() != c2.getRegistrationYear()) {
            return Integer.compare(c1.getRegistrationYear(), c2.getRegistrationYear());
        } else {
            return c1.getCustomerID().compareTo(c2.getCustomerID());
        }
    }
}
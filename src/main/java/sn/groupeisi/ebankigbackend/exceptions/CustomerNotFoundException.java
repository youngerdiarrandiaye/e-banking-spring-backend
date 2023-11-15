package sn.groupeisi.ebankigbackend.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message ) {
        super(message);

    }
}

package Restuarant;


public final class CanceledOrder implements OrderStatus {
    @Override
    public String getStatus() {
        return "Order has been canceled.";
    }
}


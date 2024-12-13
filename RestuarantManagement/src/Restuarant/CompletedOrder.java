package Restuarant;


public final class CompletedOrder implements OrderStatus {
    @Override
    public String getStatus() {
        return "Order has been completed.";
    }
}

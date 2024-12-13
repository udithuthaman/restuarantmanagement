package Restuarant;

public final class NewOrder implements OrderStatus {
    @Override
    public String getStatus() {
        return "Order is new and being prepared.";
    }
}


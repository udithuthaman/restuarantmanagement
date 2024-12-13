package Restuarant;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Order implements Printable {
    private final List<MenuItem> items;
    private final String specialInstructions;
    private final LocalDate orderDate;
    private final OrderStatus status;

    public Order(List<MenuItem> items, String specialInstructions) {
        this(items, specialInstructions, LocalDate.now());
    }

    public Order(List<MenuItem> items, String specialInstructions, LocalDate orderDate) {
        this.items = List.copyOf(items); // Defensive copying
        this.specialInstructions = specialInstructions;
        this.orderDate = orderDate;
        this.status = new NewOrder();
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public String toString() {
        return String.format("Order Date: %s, Items: %s, Instructions: %s, Status: %s",
                orderDate, items, specialInstructions, status.getStatus());
    }
}

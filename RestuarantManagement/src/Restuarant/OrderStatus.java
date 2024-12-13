package Restuarant;

public sealed interface OrderStatus permits NewOrder, CompletedOrder, CanceledOrder {
    String getStatus();
}

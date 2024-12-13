package Restuarant;

public record MenuItem(String name, double price, FoodCategory category) implements Printable {
    @Override
    public String toString() {
        return String.format("%s (%.2f) [%s]", name, price, category);
    }
}

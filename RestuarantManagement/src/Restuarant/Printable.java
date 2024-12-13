package Restuarant;

public interface Printable {
    default void print() {
        System.out.println(this.toString());
    }

    static void printHeader() {
        System.out.println("---- Order Details ----");
    }
}

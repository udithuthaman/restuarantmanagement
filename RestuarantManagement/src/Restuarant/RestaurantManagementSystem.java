package Restuarant;

import java.util.*;
import java.util.concurrent.Executors;
import java.time.LocalDate;

public class RestaurantManagementSystem {
    private final List<MenuItem> menu = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

    // Constructor
    public RestaurantManagementSystem() {
        initializeMenu();
    }

    // Overloaded Constructor
    public RestaurantManagementSystem(List<MenuItem> predefinedMenu) {
        menu.addAll(predefinedMenu);
    }

    // Initialize the menu
    public void initializeMenu() {
        menu.add(new MenuItem("Spring Rolls", 5.50, FoodCategory.APPETIZER));
        menu.add(new MenuItem("Grilled Chicken", 12.99, FoodCategory.MAIN_COURSE));
        menu.add(new MenuItem("Chocolate Cake", 6.00, FoodCategory.DESSERT));
        menu.add(new MenuItem("Lemonade", 3.50, FoodCategory.BEVERAGE));
    }

    // Display the menu
    public void displayMenu() {
        System.out.println("Restaurant Menu:");
        menu.forEach(MenuItem::print);
    }

    // Create an order using varargs
    public void createOrder(MenuItem... items) {
        List<MenuItem> selectedItems = Arrays.asList(items);
        Order newOrder = new Order(selectedItems, "None");
        orders.add(newOrder);
        System.out.println("Order created successfully!");
        newOrder.print();
    }

    // Create an order with user input
    public void createOrder(Scanner scanner) {
        List<MenuItem> selectedItems = new ArrayList<>();
        System.out.println("Select items by number (or type 0 to finish):");
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, menu.get(i));
        }

        while (true) {
            int choice = scanner.nextInt();
            if (choice == 0) break;
            if (choice > 0 && choice <= menu.size()) {
                selectedItems.add(menu.get(choice - 1));
                System.out.println("Item added!");
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.nextLine(); // Consume newline
        System.out.println("Any special instructions? (leave blank for none):");
        String specialInstructions = scanner.nextLine();

        Order newOrder = new Order(selectedItems, specialInstructions);
        orders.add(newOrder);
        System.out.println("Order created successfully!");
        newOrder.print();
    }

    // View all orders
    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet.");
            return;
        }
        System.out.println("Current Orders:");
        orders.forEach(Order::print);
    }

    // Filter orders by category
    public void filterOrdersByCategory(FoodCategory category) {
        System.out.println("Filtered Orders:");
        orders.stream()
                .filter(order -> order.getItems().stream().anyMatch(item -> item.category() == category))
                .forEach(Order::print);
    }

    // Process orders asynchronously
    public void processOrdersAsync() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            orders.forEach(order -> executor.submit(() -> System.out.println("Processing " + order)));
        }
    }

    public static void main(String[] args) {
        RestaurantManagementSystem system = new RestaurantManagementSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Restaurant Management System ---");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. View Orders");
            System.out.println("4. Filter Orders by Category");
            System.out.println("5. Process Orders Asynchronously");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> system.displayMenu();
                case 2 -> system.createOrder(scanner);
                case 3 -> system.viewOrders();
                case 4 -> {
                    System.out.println("Enter category (APPETIZER, MAIN_COURSE, DESSERT, BEVERAGE):");
                    String category = scanner.next().toUpperCase();
                    system.filterOrdersByCategory(FoodCategory.valueOf(category));
                }
                case 5 -> system.processOrdersAsync();
                case 6 -> {
                    running = false;
                    System.out.println("Thank You! Visit Again!");
                }
                default -> System.out.println("Invalid choice! Try again!");
            }
        }

        scanner.close();
    }
}

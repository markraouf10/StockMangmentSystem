import java.util.ArrayList;
import java.util.Scanner;

abstract class Product {
    private String productId;
    private String name;
    private double price;
    private int quantity;

    public Product(String productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductID() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract void displayDetails();
}

class ElectronicProduct extends Product {
    private int warrantyYears;

    public ElectronicProduct(String productId, String name, double price, int quantity, int warrantyYears) {
        super(productId, name, price, quantity);
        this.warrantyYears = warrantyYears;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }

    @Override
    public void displayDetails() {
        System.out.println("Electronic Product: " + getName() + " (ID: " + getProductID() + ")");
        System.out.println("Price: $" + getPrice());
        System.out.println("Quantity: " + getQuantity());
        System.out.println("Warranty: " + warrantyYears + " years");
    }
}

class ClothingProduct extends Product {
    private String size;

    public ClothingProduct(String productId, String name, double price, int quantity, String size) {
        super(productId, name, price, quantity);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    @Override
    public void displayDetails() {
        System.out.println("Clothing Product: " + getName() + " (ID: " + getProductID() + ")");
        System.out.println("Price: $" + getPrice());
        System.out.println("Quantity: " + getQuantity());
        System.out.println("Size: " + size);
    }
}

class Supplier {
    private String supplierId;
    private String name;
    private String contactInfo;

    public Supplier(String supplierId, String name, String contactInfo) {
        this.supplierId = supplierId;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getSupplierID() {
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}

class StockManager {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Supplier> suppliers = new ArrayList<>();

    public StockManager() {
        suppliers.add(new Supplier("SUP001", "ABC Electronics", "01095457152"));
        suppliers.add(new Supplier("SUP002", "XYZ Clothing", "01091626393"));
    }

    public void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose product type:\n1. Electronic\n2. Clothing");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Product Quantity: ");
        int quantity = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Enter Warranty Years: ");
            int warrantyYears = scanner.nextInt();
            products.add(new ElectronicProduct(productId, name, price, quantity, warrantyYears));
            System.out.println("Electronic product added successfully!");
        } else if (choice == 2) {
            scanner.nextLine();
            System.out.print("Enter Size (e.g., Small, Medium, Large): ");
            String size = scanner.nextLine();
            products.add(new ClothingProduct(productId, name, price, quantity, size));
            System.out.println("Clothing product added successfully!");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (Product product : products) {
            product.displayDetails();
            System.out.println("---------------------------");
        }
    }

    public void placeOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter quantity to order: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Supplier ID: ");
        String supplierId = scanner.nextLine();

        System.out.println("Order placed successfully for " + quantity + " " + productId + "(s) from Supplier " + supplierId + "!");
    }

    public void viewSuppliers() {
        if (suppliers.isEmpty()) {
            System.out.println("No suppliers available.");
            return;
        }

        for (Supplier supplier : suppliers) {
            System.out.println("Supplier ID: " + supplier.getSupplierID());
            System.out.println("Name: " + supplier.getName());
            System.out.println("Contact Info: " + supplier.getContactInfo());
            System.out.println("---------------------------");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Stock Management System!");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Place Order");
            System.out.println("4. View Suppliers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    placeOrder();
                    break;
                case 4:
                    viewSuppliers();
                    break;
                case 5:
                    System.out.println("Exiting Stock Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        StockManager manager = new StockManager();
        manager.run();
    }
}

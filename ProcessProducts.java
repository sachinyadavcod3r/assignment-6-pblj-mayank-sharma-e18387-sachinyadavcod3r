import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Comparator;

public class ProcessProducts {
    static class Product {
        private String name;
        private String category;
        private double price;

        public Product(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }

        @Override
        public String toString() {
            return name + " - $" + price;
        }
    }

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200.00),
            new Product("Phone", "Electronics", 800.00),
            new Product("Shirt", "Clothing", 40.00),
            new Product("Jeans", "Clothing", 60.00),
            new Product("Blender", "Home Appliances", 90.00)
        );

        // Group by category
        Map<String, List<Product>> grouped = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));

        // Most expensive in each category
        Map<String, Optional<Product>> maxByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory,
                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))));

        // Average price of all products
        double avgPrice = products.stream()
            .mapToDouble(Product::getPrice)
            .average().orElse(0);

        System.out.println("Grouped by Category:");
        grouped.forEach((cat, prods) -> {
            System.out.println(cat + ": " + prods);
        });

        System.out.println("\nMost Expensive in Each Category:");
        maxByCategory.forEach((cat, prod) -> {
            System.out.println(cat + ": " + prod.orElse(null));
        });

        System.out.println("\nAverage Price of All Products: $" + avgPrice);
    }
}

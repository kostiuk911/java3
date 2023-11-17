import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String productsFilePath = "E:\\Java\\laba3\\out\\production\\products.txt";
        String receiptFilePath = "E:\\Java\\laba3\\out\\production\\receipt..txt";


        FileService fileService = new FileService(productsFilePath, receiptFilePath);


        Store store = new Store(fileService);


        System.out.println("Initial Products:");
        displayProducts(store.getProducts());


        try {

            System.out.println("\nAvailable Products:");
            displayProducts(store.getProducts());


            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter the name of the product you want to buy: ");
            String productName = scanner.nextLine();


            Product productToBuy = findProductByName(store.getProducts(), productName);


            if (productToBuy != null) {

                System.out.print("Enter the quantity to buy: ");
                int quantityToBuy;
                try {
                    quantityToBuy = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    throw new Exception("Invalid quantity. Please enter a valid number.");
                }


                store.sellProduct(productToBuy, quantityToBuy);


                System.out.println("\nAfter Buying " + quantityToBuy + " " + productToBuy.getName() + "(s):");
                displayProducts(store.getProducts());
            } else {
                System.out.println("Product not found.");
            }
        } catch (Exception e) {
            System.err.println("Error simulating selling a product: " + e.getMessage());
        }
    }

    private static void displayProducts(List<Product> products) {
        if (products == null) {
            System.out.println("Product list is null.");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static Product findProductByName(List<Product> products, String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
}

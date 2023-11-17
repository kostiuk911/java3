import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private final String productsFilePath;
    private final String receiptFilePath;
    private final Object FILE_LOCK = new Object();

    public FileService(String productsFilePath, String receiptFilePath) {
        this.productsFilePath = productsFilePath;
        this.receiptFilePath = receiptFilePath;
    }

    public List<Product> loadProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        synchronized (FILE_LOCK) {
            try (BufferedReader reader = new BufferedReader(new FileReader(productsFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    Product product = new Product(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2]));
                    products.add(product);
                }
            }
        }
        return products;
    }

    public void saveProducts(List<Product> products) throws IOException {
        synchronized (FILE_LOCK) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(productsFilePath))) {
                for (Product product : products) {
                    writer.write(product.getName() + "," + product.getQuantity() + "," + product.getPrice());
                    writer.newLine();
                }
            }
        }
    }

    public void saveOrderHistory(Order order) throws IOException {
        // Implement saving order history to a file
    }

    public void generateReceipt(Order order) throws IOException {
        File receiptFile = new File(receiptFilePath);
        if (!receiptFile.exists()) {
            receiptFile.createNewFile();
        }

        synchronized (receiptFilePath) {
            try (PrintWriter writer = new PrintWriter(receiptFile)) {
                writer.println("Order Details:");
                writer.println("--------------------");
                for (Product product : order.getProducts()) {
                    writer.println(product.getName() + " x " + product.getQuantity() + " = " + product.getPrice() * product.getQuantity());
                }
                writer.println("Total Price: " + order.getTotalPrice());
            }
        }
    }
}

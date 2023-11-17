import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class generateReceipt {
    public static void generateReceipt(Order order) throws IOException {
        // Create receipt file
        File receiptFile = new File("E:\\Java\\laba3\\out\\production\\receipt..txt");
        if (!receiptFile.exists()) {
            receiptFile.createNewFile();
        }

        // Write data to the file
        try (PrintWriter writer = new PrintWriter(receiptFile)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            writer.println("Order Date: " + dateFormat.format(new Date()));
            writer.println("Order Details:");
            writer.println("--------------------");
            for (Product product : order.getProducts()) {
                writer.printf("%-20s x %-5d = %-10.2f\n", product.getName(), product.getQuantity(), product.getPrice() * product.getQuantity());
            }
            writer.println("--------------------");
            writer.printf("Total Price: $%-10.2f\n", order.getTotalPrice());

            // Add customer information if available
            if (order.getCustomerName() != null) {
                writer.println("Customer: " + order.getCustomerName());
            }
            if (order.getCustomerAddress() != null) {
                writer.println("Address: " + order.getCustomerAddress());
            }
            if (order.getCustomerEmail() != null) {
                writer.println("Email: " + order.getCustomerEmail());
            }
        }
    }
}

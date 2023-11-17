import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Product> products;
    private double totalPrice;
    private boolean paid;
    private String customerName;
    private String customerAddress;
    private String customerEmail;

    public Order(List<Product> products) {
        this.products = new ArrayList<>(products);
        this.totalPrice = calculateTotalPrice();
        this.paid = false;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        this.totalPrice = calculateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void addProduct(Product product) {
        products.add(product);
        totalPrice += product.getPrice() * product.getQuantity();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    private double calculateTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }

    public void generateReceipt() throws Exception {
        generateReceipt.generateReceipt(this);
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                ", totalPrice=" + totalPrice +
                ", paid=" + paid +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }
}

import java.util.List;
import java.util.ArrayList;

public class Product {

    private String name;
    private int quantity;
    private double price;
    private List<String> comments;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.comments = new ArrayList<>();
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized int getQuantity() {
        return quantity;
    }

    public synchronized void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public synchronized double getPrice() {
        return price;
    }

    public synchronized void setPrice(double price) {
        this.price = price;
    }

    public synchronized List<String> getComments() {
        return comments;
    }

    public synchronized void addComment(String comment) {
        comments.add(comment);
    }

    @Override
    public synchronized String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", comments=" + comments +
                '}';
    }
}

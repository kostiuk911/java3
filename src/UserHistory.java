import java.util.ArrayList;
import java.util.List;

public class UserHistory {

    private String username;
    private List<Order> orders;

    public UserHistory(String username) {
        this.username = username;
        this.orders = new ArrayList<>();
    }

    public synchronized String getUsername() {
        return username;
    }

    public synchronized void setUsername(String username) {
        this.username = username;
    }

    public synchronized List<Order> getOrders() {
        return orders;
    }

    public synchronized void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public synchronized void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public synchronized String toString() {
        return "UserHistory{" +
                "username='" + username + '\'' +
                ", orders=" + orders +
                '}';
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {

    private List<Product> products;
    private List<Order> orders;
    private Map<String, UserHistory> userHistories;
    private FileService fileService;

    public Store(FileService fileService) {
        this.fileService = fileService;
        this.products = loadProducts();
        this.orders = new ArrayList<>();
        this.userHistories = new HashMap<>();
    }

    public synchronized List<Product> getProducts() {
        return products != null ? new ArrayList<>(products) : new ArrayList<>();
    }

    public synchronized void setProducts(List<Product> products) {
        this.products = products;
    }

    public synchronized List<Order> getOrders() {
        return orders != null ? new ArrayList<>(orders) : new ArrayList<>();
    }

    public synchronized void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public synchronized Map<String, UserHistory> getUserHistories() {
        return userHistories != null ? new HashMap<>(userHistories) : new HashMap<>();
    }

    public synchronized void setUserHistories(Map<String, UserHistory> userHistories) {
        this.userHistories = userHistories;
    }

    public synchronized void sellProduct(Product product, int quantity) throws Exception {
        int index = products.indexOf(product);
        if (index != -1) {
            Product updatedProduct = products.get(index);
            updatedProduct.setQuantity(updatedProduct.getQuantity() - quantity);
            fileService.saveProducts(products);
        } else {
            throw new Exception("Product not found in the store.");
        }
    }

    public synchronized void editProduct(Product product) throws Exception {
        int index = products.indexOf(product);
        if (index != -1) {
            products.set(index, product);
            fileService.saveProducts(products);
        } else {
            throw new Exception("Product not found in the store.");
        }
    }

    public synchronized Order createOrder(List<Product> products) throws Exception {
        Order order = new Order(products);
        orders.add(order);
        fileService.saveOrderHistory(order);
        return order;
    }

    public synchronized void generateReceipt(Order order) throws Exception {
        fileService.generateReceipt(order);
    }

    private synchronized List<Product> loadProducts() {
        try {
            return fileService.loadProducts();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

import java.util.ArrayList;
import java.util.List;

public class CreateOrder {

    public static Order createOrder(List<Product> products) throws Exception {
        if (products.isEmpty()) {
            throw new Exception("Order cannot be empty.");
        }

        List<Product> orderedProducts = new ArrayList<>();
        double totalPrice = 0;

        for (Product product : products) {
            orderedProducts.add(new Product(product.getName(), 1, product.getPrice()));
            totalPrice += product.getPrice();
        }

        Order order = new Order(orderedProducts);
        order.setTotalPrice(totalPrice);

        for (Product product : orderedProducts) {
            if (product.getName().contains("vegetable") || product.getName().contains("fruit")) {
                product.setQuantity(product.getQuantity() + 1);
            }
        }

        for (Product product : orderedProducts) {
            if (product.getName().contains("meat") || product.getName().contains("fish")) {
                product.getComments().add("Don't forget to store products " + product.getName() + " in the refrigerator.");
            }
        }

        return order;
    }
}

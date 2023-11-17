import java.util.List;

public class SellProduct {

    private final FileService fileService;

    public SellProduct(FileService fileService) {
        this.fileService = fileService;
    }

    public synchronized void sellProduct(Product product, int quantity) throws Exception {
        if (quantity <= 0) {
            throw new Exception("Quantity must be greater than 0.");
        }

        List<Product> products = getProducts();
        if (products.isEmpty()) {
            throw new Exception("There are no products in the store.");
        }

        if (product == null) {
            throw new Exception("Product cannot be null.");
        }

        int index = products.indexOf(product);
        if (index == -1) {
            throw new Exception("Product not found in the store.");
        }

        Product storedProduct = products.get(index);

        if (storedProduct.getQuantity() < quantity) {
            throw new Exception("Not enough products in stock.");
        }

        storedProduct.setQuantity(storedProduct.getQuantity() - quantity);


        if (storedProduct.getName().contains("vegetable") || storedProduct.getName().contains("fruit")) {
            storedProduct.setQuantity(storedProduct.getQuantity() + 1);
        }


        if (storedProduct.getName().contains("meat") || storedProduct.getName().contains("fish")) {
            storedProduct.getComments().add("Don't forget to store products " + storedProduct.getName() + " in the refrigerator.");
        }

        // Save updated product information
        fileService.saveProducts(products);
    }

    private List<Product> getProducts() {
        try {
            return fileService.loadProducts();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception according to your needs
            return null;
        }
    }
}

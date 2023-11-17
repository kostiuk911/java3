import java.util.List;

public class EditProduct {

    public static void editProduct(Product product) throws Exception {
        FileService fileService = new FileService("E:\\Java\\laba3\\out\\production\\products.txt", "E:\\Java\\laba3\\out\\production\\receipt..txt");
        List<Product> products = getProducts(fileService);
        if (products.isEmpty()) {
            throw new Exception("There are no products in the store.");
        }

        if (product == null) {
            throw new Exception("Product cannot be null.");
        }

        int index = products.indexOf(product);
        if (index == -1) {
            throw new Exception("Product not found.");
        }

        products.set(index, product);

        fileService.saveProducts(products);
    }

    private static List<Product> getProducts(FileService fileService) {
        try {
            return fileService.loadProducts();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

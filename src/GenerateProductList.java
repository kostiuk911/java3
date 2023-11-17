import java.util.ArrayList;
import java.util.List;

public class GenerateProductList {

    public static void main(String[] args) {
        List<Product> productList = generateProductList();
        try {
            FileService fileService = new FileService("E:\\Java\\laba3\\out\\production\\products.txt", "E:\\Java\\laba3\\out\\production\\receipt..txt");
            fileService.saveProducts(productList);
            System.out.println("Product list generated and saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Product> generateProductList() {
        List<Product> productList = new ArrayList<>();

        productList.add(new Product("Tomato", 10, 1.5));
        productList.add(new Product("Banana", 15, 2.0));
        productList.add(new Product("Chicken", 5, 8.0));
        productList.add(new Product("Apple", 12, 1.0));
        productList.add(new Product("Fish", 8, 12.0));
        productList.add(new Product("Carrot", 20, 0.8));

        return productList;
    }
}

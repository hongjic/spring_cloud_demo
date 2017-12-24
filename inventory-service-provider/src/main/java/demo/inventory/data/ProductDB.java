package demo.inventory.data;

import java.util.ArrayList;
import java.util.List;

public enum ProductDB {
    instance;

    private List<Product> products;

    ProductDB() {
        products = new ArrayList<>();
        products.add(new Product(1, "instance noodles", 500));
    }

    public Product getProductById(int id) {
        return products
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .get();
    }
}

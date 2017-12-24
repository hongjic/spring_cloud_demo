package demo.inventory.data;

public class Product {

    private int id;
    private String name;
    private int price;

    public Product() {}

    public Product(int i, String n, int p) {
        id = i;
        name = n;
        price = p;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

package demo.service_gateway.data;

import demo.comment.data.Comment;
import demo.inventory.data.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDetail {

    private int id;
    private String name;
    private int price;
    private List<Comment> comments;

    public ProductDetail(Product product, List<Comment> comments) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.comments = new ArrayList<>();
        this.comments.addAll(comments);
    }

    public ProductDetail(int id) {
        this.id = id;
        this.comments = new ArrayList<>();
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

    public List<Comment> getComments() {
        return comments;
    }
}

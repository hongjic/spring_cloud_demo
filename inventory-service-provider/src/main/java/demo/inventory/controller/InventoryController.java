package demo.inventory.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.inventory.client.CommentClient;
import demo.inventory.data.Comment;
import demo.inventory.data.Product;
import demo.inventory.data.ProductDB;
import demo.inventory.data.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
public class InventoryController {

    @Value("${shop.open}")
    private boolean isOpen;

    @Autowired
    CommentClient commentClient;

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") int id) {
        if (!isOpen)
            return null;
        return ProductDB.instance.getProductById(id);
    }

    @GetMapping("/productDetail/{id}")
    @HystrixCommand (fallbackMethod = "getProductDetailFallback")
    public ProductDetail getProductDetailById(@PathVariable("id") int id) {
        if (!isOpen) return null;
        Product product = ProductDB.instance.getProductById(id);
        List<Comment> commentList = commentClient.getCommentsByProductId(id);
        return new ProductDetail(product, commentList);
    }

    public ProductDetail getProductDetailFallback(int id) {
        return new ProductDetail(id);
    }
}

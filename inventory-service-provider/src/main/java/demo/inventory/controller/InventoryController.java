package demo.inventory.controller;

import com.alibaba.fastjson.JSONObject;
import demo.inventory.data.Product;
import demo.inventory.data.ProductDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
public class InventoryController {

    @Value("${shop.open}")
    private boolean isOpen;

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") int id) {
        if (!isOpen)
            return null;
        return ProductDB.instance.getProductById(id);
    }
}

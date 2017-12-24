package demo.service_gateway.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.comment.data.Comment;
import demo.inventory.data.Product;
import demo.service_gateway.data.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/productDetail")
public class ProductDetailController {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public ProductDetail getProductDetailFallback(int id) {
        return new ProductDetail(id);
    }

    @GetMapping("/{id}")
    @HystrixCommand (fallbackMethod = "getProductDetailFallback")
    public ProductDetail getProductDetail(@PathVariable("id") int id) {
        Product product = restTemplate.getForObject("http://inventory-service/product/" + id, Product.class);
        List<Comment> comments = restTemplate.getForObject("http://comment-service/comments/" + id, ArrayList.class);
        System.out.println(comments.size());
        return new ProductDetail(product, comments);
    }

}

package demo.inventory.client;

import demo.inventory.data.Comment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "comment-service")
public interface CommentClient {

    @GetMapping("/comments/{id}")
    List<Comment> getCommentsByProductId(@PathVariable("id") int id);
    
}

package demo.comment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import demo.comment.data.Comment;
import demo.comment.data.CommentDB;


@RestController
public class CommentController {

    @GetMapping("/comments/{id}")
    public List<Comment> getCommentsOfProduct(@PathVariable("id") int id) {
        return CommentDB.instance.getCommentsOfProduct(id);
    }
}


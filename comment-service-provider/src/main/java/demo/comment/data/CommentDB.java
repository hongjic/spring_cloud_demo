package demo.comment.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum CommentDB {
    instance;

    private List<Comment> comments;

    CommentDB() {
        comments = new ArrayList<>();
        comments.add(new Comment("its so bad.", "chenhongji", 1));
    }

    public List<Comment> getCommentsOfProduct(int id) {
        return comments
                .stream()
                .filter(x -> x.getProductId() == id)
                .collect(Collectors.toList());
    }

    public void addComment(Comment c) {
        comments.add(c);
    }

}
package demo.comment.data;

public class Comment {
    private int productId;
    private String comment;
    private String username;

    public Comment(String c, String u, int p) {
        comment = c;
        username = u;
        productId = p;
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }

    public int getProductId() {
        return productId;
    }

}
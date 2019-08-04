package pe.edu.cibertec.retrofitgitflow.data.entities;

import java.util.List;

public class PostDetail {
    public Post post;
    public List<Comment> comments;

    public PostDetail(Post post, List<Comment> comments) {
        this.post = post;
        this.comments = comments;
    }
}

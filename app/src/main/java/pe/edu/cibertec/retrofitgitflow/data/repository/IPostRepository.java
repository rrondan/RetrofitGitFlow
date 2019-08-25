package pe.edu.cibertec.retrofitgitflow.data.repository;

import java.util.List;

import io.reactivex.Observable;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.data.entities.PostDetail;


public interface IPostRepository {

    Observable<List<Post>> getPosts();

    Observable<PostDetail> getPostDetail(int postId);

}

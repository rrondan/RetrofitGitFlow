package pe.edu.cibertec.retrofitgitflow.network;

import java.util.List;


import io.reactivex.Observable;
import pe.edu.cibertec.retrofitgitflow.data.entities.Comment;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Observable<List<Post>> getPostsRx(); // el va a hacer el onNext

    @GET("posts/{id}")
    Observable<Post> getPost(@Path("id") int postId);

    @GET("post/{id}/comments")
    Observable<List<Comment>> getComments(@Path("id") int postId);
}

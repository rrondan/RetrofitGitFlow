package pe.edu.cibertec.retrofitgitflow.network;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int postId);
}

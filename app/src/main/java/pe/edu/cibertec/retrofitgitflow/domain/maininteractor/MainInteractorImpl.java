package pe.edu.cibertec.retrofitgitflow.domain.maininteractor;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.network.ApiClient;
import pe.edu.cibertec.retrofitgitflow.network.JsonPlaceHolderApi;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractorImpl implements MainInteractor {
    @Override
    public void getAllPost(final MainCallback listener) {
        JsonPlaceHolderApi jsonPlaceHolderApi = ApiClient.getClient().create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    listener.onGetPostFailure("Code" + response.code());
                } else {
                    listener.onGetPostSuccess(response.body());
                    List<Post> posts = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                listener.onGetPostFailure(t.getMessage());
            }
        });
    }
}

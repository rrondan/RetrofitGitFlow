package pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.network.ApiClient;
import pe.edu.cibertec.retrofitgitflow.network.JsonPlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailInteractorImpl implements IPostDetailInteractor{
    @Override
    public void getPost(int postId,
                           PostDetailCallBack callBack) {
        JsonPlaceHolderApi jsonPlaceHolderApi =
                ApiClient.getClient().create(JsonPlaceHolderApi.class);
        Call<Post> call = jsonPlaceHolderApi.getPost(postId);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){ // ES CORRECTO
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onError("Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }
}

package pe.edu.cibertec.retrofitgitflow.domain.main_interactor;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.network.ApiClient;
import pe.edu.cibertec.retrofitgitflow.network.JsonPlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractorImpl
    implements IMainInteractor{
    @Override
    public void getAllPost(MainCallBack callBack) {
        JsonPlaceHolderApi jsonPlaceHolderApi =
                ApiClient.getClient().create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    callBack.onError("Code: " + response.code());
                } else {
                    callBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }
}

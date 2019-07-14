package pe.edu.cibertec.retrofitgitflow.domain.maininteractor;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;

public interface MainInteractor {

    interface MainCallback {
        void onGetPostSuccess(List<Post> posts);

        void onGetPostFailure(String errorMsg);
    }

    void getAllPost(MainCallback listener);

}

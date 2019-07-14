package pe.edu.cibertec.retrofitgitflow.domain.main_interactor;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;

public interface IMainInteractor {

    interface MainCallBack{
        void onSuccess(List<Post> postList);
        void onError(String errorMsg);
    }

    void getAllPost(MainCallBack callBack);
}

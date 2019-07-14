package pe.edu.cibertec.retrofitgitflow.presentation.main;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;

public interface MainContract {

    interface MainView{
        void showError(String errorMsg);
        void showProgressBar();
        void hideProgressBar();
        void getAllPostSuccess(List<Post> postList);
        void goToDetailPost(int postId);
    }

    interface Presenter{
        void attachView(MainView view);
        void dettachView();
        boolean isViewAttached();
        void getAllPost();
    }

}

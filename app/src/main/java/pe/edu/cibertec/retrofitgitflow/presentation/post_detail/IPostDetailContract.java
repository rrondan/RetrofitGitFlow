package pe.edu.cibertec.retrofitgitflow.presentation.post_detail;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.data.entities.PostDetail;
import pe.edu.cibertec.retrofitgitflow.presentation.main.IMainContract;

public interface IPostDetailContract {
    interface IView{
        void showError(String errorMsg);
        void showProgressBar();
        void hideProgressBar();
        void getPostDetailSuccess(PostDetail post);
    }
    interface IPresenter{
        void attachView(IPostDetailContract.IView view);
        void detachView();
        boolean isViewAttached();
        void getPost(int postId);
    }
}

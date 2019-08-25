package pe.edu.cibertec.retrofitgitflow.presentation.activities.post_detail;

import pe.edu.cibertec.retrofitgitflow.data.entities.PostDetail;

public interface IPostDetailContract {
    interface IView{
        void showError(String errorMsg);
        void showProgressBar();
        void hideProgressBar();
        void getPostDetailSuccess(PostDetail post);
    }
    interface IPresenter{
        void attachView(IView view);
        void detachView();
        boolean isViewAttached();
        void getPost(int postId);
    }
}

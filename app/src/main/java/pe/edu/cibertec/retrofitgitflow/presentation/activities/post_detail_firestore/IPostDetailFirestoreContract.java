package pe.edu.cibertec.retrofitgitflow.presentation.activities.post_detail_firestore;

import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;


public interface IPostDetailFirestoreContract {
    interface IView{
        void showError(String errorMsg);
        void showProgressBar();
        void hideProgressBar();
        void getPostDetailSuccess(NewPost post);
    }
    interface IPresenter{
        void attachView(IView view);
        void detachView();
        boolean isViewAttached();
        void getPost(String id);
    }
}

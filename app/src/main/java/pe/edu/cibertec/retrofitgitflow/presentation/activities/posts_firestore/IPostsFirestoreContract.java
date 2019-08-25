package pe.edu.cibertec.retrofitgitflow.presentation.activities.posts_firestore;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;

public interface IPostsFirestoreContract {

    interface IView{
        void showError(String errorMsg);
        void showProgressBar();
        void hideProgressBar();
        void showNoPost();
        void hideNoPost();
        void refreshRecyclerView(List<NewPost> postList);
        void gotToDetailPost(String postUid);
    }
    interface IPresenter{
        void attachView(IView view);
        void detachView();
        boolean isViewAttached();
        void getAllPost();
    }

}

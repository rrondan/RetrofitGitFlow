package pe.edu.cibertec.retrofitgitflow.presentation.activities.create_post_firestore;

public interface ICreatePostFirestoreContract {
    interface IView {
        void showError(String errorMsg);

        void showProgressDialog();

        void hideProgressDialog();

        void onSuccessCreate();
    }

    interface IPresenter {
        void attachView(IView view);

        void detachView();

        boolean isViewAttached();

        void createPost(String title, String content, String path);
    }
}

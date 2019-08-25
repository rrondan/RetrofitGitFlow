package pe.edu.cibertec.retrofitgitflow.presentation.activities.create_post_firestore;

import android.text.TextUtils;
import javax.inject.Inject;

public class CreatePostFirestorePresenter implements ICreatePostFirestoreContract.IPresenter {

    ICreatePostFirestoreContract.IView view;

    @Inject
    public CreatePostFirestorePresenter() {
    }

    @Override
    public void attachView(ICreatePostFirestoreContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void createPost(String title, String content, String path) {
        view.showProgressDialog();
        if(TextUtils.isEmpty(title) || content.isEmpty()){
            if(isViewAttached()) view.showError("Ingrese datos porfavor");
            return;
        }
    }


}

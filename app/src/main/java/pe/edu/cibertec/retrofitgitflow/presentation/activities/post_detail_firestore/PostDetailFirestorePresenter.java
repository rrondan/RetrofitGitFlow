package pe.edu.cibertec.retrofitgitflow.presentation.activities.post_detail_firestore;

import javax.inject.Inject;

public class PostDetailFirestorePresenter implements IPostDetailFirestoreContract.IPresenter {

    IPostDetailFirestoreContract.IView view;

    @Inject
    public PostDetailFirestorePresenter() {
    }

    @Override
    public void attachView(IPostDetailFirestoreContract.IView view) {
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
    public void getPost(String id) {
        if(id == null || id.isEmpty()){
            view.showError("No hay id de Post");
            return;
        }
        view.showProgressBar();

    }
}

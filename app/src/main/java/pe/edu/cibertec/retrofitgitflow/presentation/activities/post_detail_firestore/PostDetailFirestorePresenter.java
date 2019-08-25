package pe.edu.cibertec.retrofitgitflow.presentation.activities.post_detail_firestore;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_firestore_interactor.IPostDetailFirestoreInteractor;


public class PostDetailFirestorePresenter implements IPostDetailFirestoreContract.IPresenter {

    IPostDetailFirestoreContract.IView view;

    @Inject
    IPostDetailFirestoreInteractor interactor;

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
        interactor.getPost(id, task -> {
            if(isViewAttached()) {
                view.hideProgressBar();
                if (task.isSuccessful()) {
                    NewPost post = task.getResult().toObject(NewPost.class);
                    view.getPostDetailSuccess(post);
                } else {
                    view.showError(task.getException().getMessage());
                }
            }
        });
    }
}

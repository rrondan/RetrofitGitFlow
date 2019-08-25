package pe.edu.cibertec.retrofitgitflow.presentation.activities.posts_firestore;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;


public class PostsFirestorePresenter implements IPostsFirestoreContract.IPresenter {

    private IPostsFirestoreContract.IView view;
    private ListenerRegistration registration;
    private List<NewPost> postList;
    int index;


    @Inject
    public PostsFirestorePresenter() {
        postList = new ArrayList<>();
    }

    @Override
    public void attachView(IPostsFirestoreContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(registration != null){
            registration.remove();
        }
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void getAllPost() {
        if(isViewAttached()) view.showProgressBar();

    }
}

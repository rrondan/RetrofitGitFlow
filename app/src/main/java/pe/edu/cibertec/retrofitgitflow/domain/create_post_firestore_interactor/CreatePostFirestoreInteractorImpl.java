package pe.edu.cibertec.retrofitgitflow.domain.create_post_firestore_interactor;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;
import pe.edu.cibertec.retrofitgitflow.data.repository.IPostFirestoreRepository;

public class CreatePostFirestoreInteractorImpl implements ICreatePostFirestoreInteractor {

    private final IPostFirestoreRepository repository;

    @Inject
    public CreatePostFirestoreInteractorImpl(IPostFirestoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createPost(NewPost post, OnCompleteListener<DocumentReference> onComplete) {
        repository.createPost(post, onComplete);
    }
}

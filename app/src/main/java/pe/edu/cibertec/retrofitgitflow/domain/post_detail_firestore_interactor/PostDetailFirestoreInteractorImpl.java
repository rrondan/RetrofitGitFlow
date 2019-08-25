package pe.edu.cibertec.retrofitgitflow.domain.post_detail_firestore_interactor;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.data.repository.IPostFirestoreRepository;

public class PostDetailFirestoreInteractorImpl implements IPostDetailFirestoreInteractor {

    private final IPostFirestoreRepository repository;

    @Inject
    public PostDetailFirestoreInteractorImpl(IPostFirestoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getPost(String uid, OnCompleteListener<DocumentSnapshot> onComplete) {
        repository.getPost(uid, onComplete);
    }
}

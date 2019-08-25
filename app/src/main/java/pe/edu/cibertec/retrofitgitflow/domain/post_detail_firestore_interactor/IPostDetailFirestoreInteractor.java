package pe.edu.cibertec.retrofitgitflow.domain.post_detail_firestore_interactor;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;

public interface IPostDetailFirestoreInteractor {
    void getPost(String uid, OnCompleteListener<DocumentSnapshot> onComplete);
}

package pe.edu.cibertec.retrofitgitflow.data.repository;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;

public interface IPostFirestoreRepository {

    Query getAllPosts();

    void getPost(String uid,  OnCompleteListener<DocumentSnapshot> onComplete);

    void createPost(NewPost post, OnCompleteListener<DocumentReference> onComplete);

}

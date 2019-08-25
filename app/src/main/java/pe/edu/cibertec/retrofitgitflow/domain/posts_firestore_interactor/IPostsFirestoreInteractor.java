package pe.edu.cibertec.retrofitgitflow.domain.posts_firestore_interactor;

import com.google.firebase.firestore.Query;

public interface IPostsFirestoreInteractor {
    Query getAllPost();
}

package pe.edu.cibertec.retrofitgitflow.data.repository;

import com.google.firebase.firestore.Query;

public interface IPostFirestoreRepository {

    Query getAllPosts();

}

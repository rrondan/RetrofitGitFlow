package pe.edu.cibertec.retrofitgitflow.presentation.activities.create_post_firestore;

import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;

import pe.edu.cibertec.retrofitgitflow.domain.create_post_firestore_interactor.ICreatePostFirestoreInteractor;
import pe.edu.cibertec.retrofitgitflow.presentation.utils.PhotoUtils;

public class CreatePostFirestorePresenter implements ICreatePostFirestoreContract.IPresenter {

    ICreatePostFirestoreContract.IView view;

    @Inject
    ICreatePostFirestoreInteractor interactor;
    @Inject
    FirebaseAuth firebaseAuth;
    @Inject
    FirebaseStorage storage;

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
        if(path != null && !path.isEmpty()){
            StorageReference storageReference = storage.getReference();
            String name = "JPEG_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_.jpg";
            StorageReference imageReference = storageReference.child("images/" + name);
            UploadTask uploadTask = imageReference.putBytes(PhotoUtils.getBytesPhoto(path));
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return imageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri donwloadUri = task.getResult();
                        createNewPost(title, content, donwloadUri.toString());
                    }else {
                        if(isViewAttached()) view.showError("Ocurrio un error al subir la imagen");
                        createNewPost(title,content,null);
                    }
                }
            });
        } else {
            createNewPost(title,content,null);
        }

    }

    private void createNewPost(String title, String content, String path){
        NewPost post = new NewPost();
        post.setTitle(title);
        post.setContent(content);
        post.setUserUid(firebaseAuth.getUid());
        if(path != null && !path.isEmpty()) {
            post.setPathPhoto(path);
        }
        interactor.createPost(post, task -> {
            if(isViewAttached()) {
                view.hideProgressDialog();
                if (task.isSuccessful()) {
                    view.onSuccessCreate();
                } else {
                    view.showError(task.getException().getMessage());
                }
            }
        });
    }
}

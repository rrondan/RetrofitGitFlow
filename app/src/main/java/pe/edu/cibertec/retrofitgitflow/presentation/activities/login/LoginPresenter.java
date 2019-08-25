package pe.edu.cibertec.retrofitgitflow.presentation.activities.login;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class LoginPresenter implements ILoginContract.IPresenter {

    private ILoginContract.IView view;
    private final FirebaseAuth firebaseAuth;

    @Inject
    public LoginPresenter(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }


    @Override
    public void attachView(ILoginContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void checkUserLogged() {
        if(firebaseAuth.getCurrentUser() != null && isViewAttached()){
            view.goToMenu();
        }
    }

    @Override
    public void login(String username, String password) {
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            if(isViewAttached()) view.showError("No deje los campos vacios");
        } else {
            if(isViewAttached()) view.showProgressDialog();
            firebaseAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(task -> {
                        if(isViewAttached()) {
                            view.hideProgressDialog();
                            if (task.isSuccessful() && task.getResult() != null) {
                                view.goToMenu();
                            } else {
                                view.showError(task.getException().getMessage());
                            }
                        }
                    });
        }
    }
}

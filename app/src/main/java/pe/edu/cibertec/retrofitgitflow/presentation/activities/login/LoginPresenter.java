package pe.edu.cibertec.retrofitgitflow.presentation.activities.login;

import android.text.TextUtils;

import javax.inject.Inject;

public class LoginPresenter implements ILoginContract.IPresenter {

    private ILoginContract.IView view;

    @Inject
    public LoginPresenter() {

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

    }

    @Override
    public void login(String username, String password) {
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            if(isViewAttached()) view.showError("No deje los campos vacios");
        } else {
            if(isViewAttached()) view.showProgressDialog();

        }
    }
}

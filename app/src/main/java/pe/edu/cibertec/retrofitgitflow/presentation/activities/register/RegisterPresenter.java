package pe.edu.cibertec.retrofitgitflow.presentation.activities.register;

import android.text.TextUtils;

import javax.inject.Inject;

public class RegisterPresenter implements IRegisterContract.IPresenter {

    private IRegisterContract.IView view;


    @Inject
    public RegisterPresenter() {

    }

    @Override
    public void attachView(IRegisterContract.IView view) {
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
    public void register(String username, String password) {
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            if(isViewAttached()) view.showError("No deje los campos vacios");
        } else {
            if(isViewAttached()) view.showProgressDialog();

        }
    }
}

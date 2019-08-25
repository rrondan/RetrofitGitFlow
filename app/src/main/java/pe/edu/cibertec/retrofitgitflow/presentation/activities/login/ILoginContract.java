package pe.edu.cibertec.retrofitgitflow.presentation.activities.login;

public interface ILoginContract {
    interface IView{
        void showError(String errorMsg);
        void showProgressDialog();
        void hideProgressDialog();
        void goToMenu();
    }
    interface IPresenter{
        void attachView(IView view);
        void detachView();
        boolean isViewAttached();
        void checkUserLogged();
        void login(String username, String password);
    }
}

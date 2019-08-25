package pe.edu.cibertec.retrofitgitflow.presentation.activities.register;

public interface IRegisterContract {
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
        void register(String username, String password);
    }
}

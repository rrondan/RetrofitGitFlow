package pe.edu.cibertec.retrofitgitflow.presentation.main.presenter;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.domain.maininteractor.MainInteractor;
import pe.edu.cibertec.retrofitgitflow.presentation.main.MainContract;

public class MainPresenter implements MainContract.Presenter {

    MainContract.MainView view;
    MainInteractor mainInteractor;

    public MainPresenter(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    @Override
    public void attachView(MainContract.MainView view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void getAllPost() {
        view.showProgressBar();
        mainInteractor.getAllPost(new MainInteractor.MainCallback() {
            @Override
            public void onGetPostSuccess(List<Post> posts) {
                if(isViewAttached()) {
                    view.getAllPostSuccess(posts);
                    view.hideProgressBar();
                }
            }

            @Override
            public void onGetPostFailure(String errorMsg) {
                if(isViewAttached()) {
                    view.showError(errorMsg);
                    view.hideProgressBar();
                }
            }
        });
    }
}

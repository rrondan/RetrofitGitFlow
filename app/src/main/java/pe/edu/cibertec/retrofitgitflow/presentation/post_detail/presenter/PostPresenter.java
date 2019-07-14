package pe.edu.cibertec.retrofitgitflow.presentation.post_detail.presenter;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.IPostDetailInteractor;
import pe.edu.cibertec.retrofitgitflow.presentation.main.IMainContract;
import pe.edu.cibertec.retrofitgitflow.presentation.post_detail.IPostDetailContract;

public class PostPresenter implements
        IPostDetailContract.IPresenter {

    IPostDetailContract.IView view;
    IPostDetailInteractor interactor;

    public PostPresenter(IPostDetailInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IPostDetailContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public boolean isViewAttached() {
        return this.view != null;
    }

    @Override
    public void getPost(int postId) {
        view.showProgressBar();
        interactor.getPost(postId, new IPostDetailInteractor.PostDetailCallBack() {
            @Override
            public void onSuccess(Post post) {
                if(isViewAttached()) {
                    view.getPostSuccess(post);
                    view.hideProgressBar();
                }
            }

            @Override
            public void onError(String errorMsg) {
                if(isViewAttached()) {
                    view.showError(errorMsg);
                    view.hideProgressBar();
                }
            }
        });
    }
}

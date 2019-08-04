package pe.edu.cibertec.retrofitgitflow.presentation.post_detail.presenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.IPostDetailInteractor;
import pe.edu.cibertec.retrofitgitflow.presentation.main.IMainContract;
import pe.edu.cibertec.retrofitgitflow.presentation.post_detail.IPostDetailContract;

public class PostPresenter implements
        IPostDetailContract.IPresenter {

    IPostDetailContract.IView view;
    private final IPostDetailInteractor interactor;
    private Disposable disposable;

    @Inject
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
        if(disposable != null){
            disposable.dispose();
        }
    }

    @Override
    public boolean isViewAttached() {
        return this.view != null;
    }

    @Override
    public void getPost(int postId) {
        view.showProgressBar();
        interactor.getPost(postId)
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Post post) {
                        if(isViewAttached()) {
                            view.hideProgressBar();
                            view.getPostSuccess(post);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()) {
                            view.hideProgressBar();
                            view.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

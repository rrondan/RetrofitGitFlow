package pe.edu.cibertec.retrofitgitflow.presentation.activities.post_detail;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import pe.edu.cibertec.retrofitgitflow.data.entities.PostDetail;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.IPostDetailInteractor;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.post_detail.IPostDetailContract;

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
        interactor.getPostDetail(postId)
                .subscribe(new Observer<PostDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(PostDetail postDetail) {
                        if(isViewAttached()) {
                            view.hideProgressBar();
                            view.getPostDetailSuccess(postDetail);
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

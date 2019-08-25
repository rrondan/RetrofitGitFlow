package pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import pe.edu.cibertec.retrofitgitflow.data.entities.PostDetail;
import pe.edu.cibertec.retrofitgitflow.data.repository.IPostRepository;

public class PostDetailInteractorImpl implements IPostDetailInteractor{

    private final IPostRepository postRepository;
    private final Scheduler uiThread;
    private final Scheduler executorThread;

    @Inject
    public PostDetailInteractorImpl(IPostRepository postRepository, Scheduler uiThread, Scheduler executorThread) {
        this.postRepository = postRepository;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<PostDetail> getPostDetail(int postId) {
        return postRepository.getPostDetail(postId)
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}

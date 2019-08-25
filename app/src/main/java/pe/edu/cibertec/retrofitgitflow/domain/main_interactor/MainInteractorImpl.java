package pe.edu.cibertec.retrofitgitflow.domain.main_interactor;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.data.repository.IPostRepository;
import pe.edu.cibertec.retrofitgitflow.network.JsonPlaceHolderApi;

@Singleton
public class MainInteractorImpl implements IMainInteractor{

    private final IPostRepository postRepository;
    private final Scheduler uiThread;
    private final Scheduler executorThread;

    @Inject
    public MainInteractorImpl(IPostRepository postRepository, Scheduler uiThread, Scheduler executorThread) {
        this.postRepository = postRepository;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<List<Post>> getAllPost() {
        return postRepository.getPosts()
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}

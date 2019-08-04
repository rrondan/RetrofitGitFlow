package pe.edu.cibertec.retrofitgitflow.domain.main_interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.network.JsonPlaceHolderApi;

public class MainInteractorImpl
    implements IMainInteractor{

    private final JsonPlaceHolderApi jsonPlaceHolderApi;
    private final Scheduler uiThread;
    private final Scheduler executorThread;

    @Inject
    public MainInteractorImpl(JsonPlaceHolderApi jsonPlaceHolderApi,
                              Scheduler uiThread,
                              Scheduler executorThread) {
        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<List<Post>> getAllPost() {
        return jsonPlaceHolderApi.getPosts()
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}

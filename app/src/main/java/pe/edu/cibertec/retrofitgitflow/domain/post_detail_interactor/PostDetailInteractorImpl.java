package pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.BiFunction;
import pe.edu.cibertec.retrofitgitflow.data.entities.Comment;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.data.entities.PostDetail;
import pe.edu.cibertec.retrofitgitflow.network.ApiClient;
import pe.edu.cibertec.retrofitgitflow.network.JsonPlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailInteractorImpl implements IPostDetailInteractor{

    private final JsonPlaceHolderApi jsonPlaceHolderApi;
    private final Scheduler uiThread;
    private final Scheduler executorThread;

    @Inject
    public PostDetailInteractorImpl(JsonPlaceHolderApi jsonPlaceHolderApi, Scheduler uiThread, Scheduler executorThread) {
        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<PostDetail> getPostDetail(int postId) {
        return Observable.zip(jsonPlaceHolderApi.getPost(postId), jsonPlaceHolderApi.getComments(postId),
                new BiFunction<Post, List<Comment>, PostDetail>() {
                    @Override
                    public PostDetail apply(Post post, List<Comment> comments) throws Exception {
                        return new PostDetail(post, comments);
                    }
                }
                ).observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}

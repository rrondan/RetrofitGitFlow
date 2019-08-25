package pe.edu.cibertec.retrofitgitflow.data.repository.impl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import pe.edu.cibertec.retrofitgitflow.data.entities.Comment;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.data.entities.PostDetail;
import pe.edu.cibertec.retrofitgitflow.data.repository.IPostRepository;
import pe.edu.cibertec.retrofitgitflow.data.network.JsonPlaceHolderApi;


public class PostRepositoryImpl implements IPostRepository {

    private final JsonPlaceHolderApi jsonPlaceHolderApi;

    @Inject
    public PostRepositoryImpl(JsonPlaceHolderApi jsonPlaceHolderApi) {
        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
    }

    @Override
    public Observable<List<Post>> getPosts() {
        return jsonPlaceHolderApi.getPostsRx();
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
        );
    }
}

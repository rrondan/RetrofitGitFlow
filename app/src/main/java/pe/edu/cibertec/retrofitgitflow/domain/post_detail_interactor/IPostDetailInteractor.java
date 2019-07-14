package pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.data.entities.Post;

public interface IPostDetailInteractor {

    interface PostDetailCallBack{
        void onSuccess(Post post);
        void onError(String errorMsg);
    }

    interface PostDetailCommentCallBack{
        void onSuccess(List<Comment> comments);
        void onError(String errorMsg);
    }

    void getPost(int postId, PostDetailCallBack callBack);

    void getCommentsOfPost(int postId,
                           PostDetailCommentCallBack callBack);
}

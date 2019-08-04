package pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor;

import java.util.List;

import io.reactivex.Observable;
import pe.edu.cibertec.retrofitgitflow.data.entities.Comment;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.data.entities.PostDetail;

public interface IPostDetailInteractor {
    Observable<PostDetail> getPostDetail(int postId);
}

package pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor;

import java.util.List;

import io.reactivex.Observable;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;

public interface IPostDetailInteractor {
    Observable<Post> getPost(int postId);
}

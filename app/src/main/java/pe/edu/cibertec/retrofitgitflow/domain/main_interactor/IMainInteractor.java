package pe.edu.cibertec.retrofitgitflow.domain.main_interactor;

import java.util.List;
import io.reactivex.Observable;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;

public interface IMainInteractor {
    Observable<List<Post>> getAllPost();
}

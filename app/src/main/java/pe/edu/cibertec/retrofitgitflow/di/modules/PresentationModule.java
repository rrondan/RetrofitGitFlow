package pe.edu.cibertec.retrofitgitflow.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import pe.edu.cibertec.retrofitgitflow.data.repository.IPostFirestoreRepository;
import pe.edu.cibertec.retrofitgitflow.data.repository.IPostRepository;
import pe.edu.cibertec.retrofitgitflow.di.scope.PerActivity;
import pe.edu.cibertec.retrofitgitflow.domain.main_interactor.IMainInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.main_interactor.MainInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.domain.create_post_firestore_interactor.ICreatePostFirestoreInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.create_post_firestore_interactor.CreatePostFirestoreInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_firestore_interactor.IPostDetailFirestoreInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_firestore_interactor.PostDetailFirestoreInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.IPostDetailInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.PostDetailInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.domain.posts_firestore_interactor.IPostsFirestoreInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.posts_firestore_interactor.PostFirestoreInteractorImpl;

@Module
public class PresentationModule {

    @PerActivity
    @Provides
    IMainInteractor provideMainInteractor(IPostRepository repository,
                                          @Named("ui_thread") Scheduler uiThread,
                                          @Named("executor_thread") Scheduler executorThread){
        return new MainInteractorImpl(repository, uiThread, executorThread);
    }

    @PerActivity
    @Provides
    IPostDetailInteractor providePostDetailInteractor(IPostRepository repository,
                                                      @Named("ui_thread") Scheduler uiThread,
                                                      @Named("executor_thread") Scheduler executorThread){
        return new PostDetailInteractorImpl(repository,uiThread,executorThread);
    }

    @PerActivity
    @Provides
    IPostsFirestoreInteractor providePostsFirestoreInteractor(IPostFirestoreRepository repository){
        return new PostFirestoreInteractorImpl(repository);
    }

    @PerActivity
    @Provides
    ICreatePostFirestoreInteractor provideCreatePostFirestoreInteractor(IPostFirestoreRepository repository){
        return new CreatePostFirestoreInteractorImpl(repository);
    }

    @PerActivity
    @Provides
    IPostDetailFirestoreInteractor providePostDetailFirestoreInteractor(IPostFirestoreRepository repository){
        return new PostDetailFirestoreInteractorImpl(repository);
    }

}

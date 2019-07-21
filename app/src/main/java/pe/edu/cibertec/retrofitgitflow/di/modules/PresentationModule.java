package pe.edu.cibertec.retrofitgitflow.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pe.edu.cibertec.retrofitgitflow.domain.main_interactor.IMainInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.main_interactor.MainInteractorImpl;

@Module
public class PresentationModule {


    @Provides
    IMainInteractor provideMainInteractor(){
        return new MainInteractorImpl();
    }

}

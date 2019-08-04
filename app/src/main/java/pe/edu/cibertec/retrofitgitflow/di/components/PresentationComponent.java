package pe.edu.cibertec.retrofitgitflow.di.components;

import javax.inject.Singleton;

import dagger.Component;
import pe.edu.cibertec.retrofitgitflow.di.modules.ApplicationModule;
import pe.edu.cibertec.retrofitgitflow.di.modules.PresentationModule;
import pe.edu.cibertec.retrofitgitflow.di.scope.PerActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.main.view.MainActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.post_detail.view.PostDetailActivity;

@PerActivity
@Component (modules = PresentationModule.class, dependencies = ApplicationComponent.class)
public interface PresentationComponent {
    void inject(MainActivity mainActivity);
    void inject(PostDetailActivity postDetailActivity);
}

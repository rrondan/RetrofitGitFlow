package pe.edu.cibertec.retrofitgitflow.di.components;

import javax.inject.Singleton;

import dagger.Component;
import pe.edu.cibertec.retrofitgitflow.di.modules.PresentationModule;
import pe.edu.cibertec.retrofitgitflow.di.scope.PerActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.main.view.MainActivity;


@PerActivity
@Component (modules = PresentationModule.class,
            dependencies = ApplicationComponent.class)
public interface PresentationComponent {

    void inject(MainActivity mainActivity);

}

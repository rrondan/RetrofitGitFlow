package pe.edu.cibertec.retrofitgitflow;

import android.app.Application;

import pe.edu.cibertec.retrofitgitflow.di.components.ApplicationComponent;
import pe.edu.cibertec.retrofitgitflow.di.components.DaggerApplicationComponent;
import pe.edu.cibertec.retrofitgitflow.di.components.DaggerPresentationComponent;
import pe.edu.cibertec.retrofitgitflow.di.components.PresentationComponent;
import pe.edu.cibertec.retrofitgitflow.di.modules.ApplicationModule;
import pe.edu.cibertec.retrofitgitflow.di.modules.PresentationModule;

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        inicializarComponent();
    }

    void inicializarComponent(){
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

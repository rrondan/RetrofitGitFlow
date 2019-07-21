package pe.edu.cibertec.retrofitgitflow;

import android.app.Application;

import pe.edu.cibertec.retrofitgitflow.di.components.DaggerPresentationComponent;
import pe.edu.cibertec.retrofitgitflow.di.components.PresentationComponent;
import pe.edu.cibertec.retrofitgitflow.di.modules.PresentationModule;

public class MyApplication extends Application {

    private PresentationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        inicializarComponent();
    }

    void inicializarComponent(){
        appComponent = DaggerPresentationComponent
                .builder()
                .presentationModule(new PresentationModule())
                .build();
    }

    public PresentationComponent getAppComponent() {
        return appComponent;
    }
}

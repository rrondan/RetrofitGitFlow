package pe.edu.cibertec.retrofitgitflow.di.components;


import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pe.edu.cibertec.retrofitgitflow.di.modules.ApplicationModule;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();
    Context exposeContext();

    @Named("ui_thread") Scheduler uiThread();
    @Named("executor_thread") Scheduler executorThread();
}

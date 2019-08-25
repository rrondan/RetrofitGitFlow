package pe.edu.cibertec.retrofitgitflow.di.components;


import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pe.edu.cibertec.retrofitgitflow.data.repository.IPostRepository;
import pe.edu.cibertec.retrofitgitflow.di.modules.ApplicationModule;
import pe.edu.cibertec.retrofitgitflow.network.JsonPlaceHolderApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();
    Context exposeContext();
    JsonPlaceHolderApi exposeJsonPlaceholderApi();
    FirebaseAuth exposeFirebaseAuth();
    FirebaseFirestore exposeFirestore();
    IPostRepository exposePostRepository();

    @Named("ui_thread") Scheduler uiThread();
    @Named("executor_thread") Scheduler executorThread();
}

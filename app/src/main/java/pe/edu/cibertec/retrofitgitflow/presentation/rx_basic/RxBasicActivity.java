package pe.edu.cibertec.retrofitgitflow.presentation.rx_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pe.edu.cibertec.retrofitgitflow.R;

public class RxBasicActivity extends AppCompatActivity {

    private static final String TAG =
            RxBasicActivity.class.getSimpleName();
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_basic);
        crearObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(crearObserver());
    }

    private String tareaLargaDuracion(){
        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Tarea de Larga Hilo: " + Thread.currentThread().getName());
        return "Tarea Larga Duración";
    }

    //Creamos el Observable
    private Observable crearObservable(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter)
                    throws Exception {
                try{
                    emitter.onNext("Primer dato del Observable");
                    emitter.onNext("Segundo dato del Observable");
                    emitter.onNext(tareaLargaDuracion());
                    emitter.onComplete();
                }catch (Exception e){
                    emitter.onError(e);
                }
            }
        });
    }

    //Crear el Observador
    private Observer crearObserver(){
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Observer: " + s + " Hilo: "
                        + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete: Hilo: "
                        + Thread.currentThread().getName());
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable != null){
            disposable.dispose();
        }
    }
}

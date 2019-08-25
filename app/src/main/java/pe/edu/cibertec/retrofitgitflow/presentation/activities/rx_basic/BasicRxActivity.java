package pe.edu.cibertec.retrofitgitflow.presentation.activities.rx_basic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pe.edu.cibertec.retrofitgitflow.R;
import pe.edu.cibertec.retrofitgitflow.base.BaseActivity;

public class BasicRxActivity extends BaseActivity {

    private static final String TAG = BasicRxActivity.class.getSimpleName();
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_rx_basic;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        //tareaLargaDuracion();
        crearObservable()
                .subscribeOn(Schedulers.io()) // Que hilo va a susbcribirse
                .observeOn(AndroidSchedulers.mainThread()) // En que hilo se va a ejecutar
                .subscribe(crearObserver());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }


    //este es el observador
    private Observer crearObserver(){
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //Unir el subscritor con el el observer y en el destroy se liberará la relación y evitar el memory leak
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s + " Hilo: " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: " + " Hilo: " + Thread.currentThread().getName());
            }
        };
    }

    private Observable crearObservable(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                try{
                    emitter.onNext("Primer Dato del Observable");
                    emitter.onNext("Segundo Dato del Observable");
                    emitter.onNext(tareaLargaDuracion());
                    emitter.onComplete();
                }catch (Exception e){
                    emitter.onError(e);
                }
            }
        });
    }

    private String tareaLargaDuracion(){
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.e(TAG, "Observable: Hilo: " + Thread.currentThread().getName());
        return "Tarea Lara Duración";
    }
}

package pe.edu.cibertec.retrofitgitflow.presentation.activities.menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import pe.edu.cibertec.retrofitgitflow.R;
import pe.edu.cibertec.retrofitgitflow.base.BaseActivity;
import pe.edu.cibertec.retrofitgitflow.di.components.DaggerPresentationComponent;
import pe.edu.cibertec.retrofitgitflow.di.modules.PresentationModule;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.main.MainActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.posts_firestore.PostsFirestoreActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.rx_basic.BasicRxActivity;

public class MenuActivity extends BaseActivity {

    Button rxJavaBasicButton;
    Button retrofitButton;
    Button firestoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_menu;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        rxJavaBasicButton = findViewById(R.id.btn_rxbasic);
        retrofitButton = findViewById(R.id.btn_retrofit);
        firestoreButton = findViewById(R.id.btn_firebase);
        setListeners();
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPresentationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presentationModule(new PresentationModule())
                .build().inject(this);
    }

    private void setListeners(){
        rxJavaBasicButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), BasicRxActivity.class);
            startActivity(intent);
        });
        retrofitButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        firestoreButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PostsFirestoreActivity.class);
            startActivity(intent);
        });
    }
}

package pe.edu.cibertec.retrofitgitflow.paid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.JsonPlaceHolderApi;
import pe.edu.cibertec.retrofitgitflow.Post;
import pe.edu.cibertec.retrofitgitflow.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostDetailActivity extends AppCompatActivity {

    TextView textViewUserId;
    TextView textViewId;
    TextView textViewTitle;
    TextView textViewBody;

    int postId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        postId = getIntent().getIntExtra("post_id", -1);
        if(postId == -1){
            Toast.makeText(this, "No has mandado un post id", Toast.LENGTH_SHORT).show();
            finish();
        }
        textViewUserId = findViewById(R.id.textViewUserId);
        textViewId = findViewById(R.id.textViewId);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewBody = findViewById(R.id.textViewBody);
        callService();
    }

    private void callService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Post> call = jsonPlaceHolderApi.getPostDetail(postId);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textViewTitle.setText("Code: " + response.code());
                } else {
                    Post postResult = response.body();
                    textViewUserId.setText(String.valueOf(postResult.getUserId()));
                    textViewId.setText(String.valueOf(postResult.getId()));
                    textViewTitle.setText(postResult.getTitle());
                    textViewBody.setText(postResult.getText());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewTitle.setText("Error: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}

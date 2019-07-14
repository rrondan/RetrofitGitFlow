package pe.edu.cibertec.retrofitgitflow.presentation.post_detail.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import pe.edu.cibertec.retrofitgitflow.R;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.PostDetailInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.presentation.post_detail.IPostDetailContract;
import pe.edu.cibertec.retrofitgitflow.presentation.post_detail.presenter.PostPresenter;

public class PostDetailActivity extends AppCompatActivity
    implements IPostDetailContract.IView {

    PostPresenter presenter;
    TextView idTextView;
    TextView userIdTextView;
    TextView titleTextView;
    TextView bodyTextView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        idTextView = findViewById(R.id.idTextView);
        userIdTextView = findViewById(R.id.userIdTextView);
        titleTextView = findViewById(R.id.titleTextView);
        bodyTextView = findViewById(R.id.bodyTextView);
        progressBar = findViewById(R.id.progressBar);

        int id = getIntent().getIntExtra("post_id", -1);
        if(id == -1){
            showError("No Nos llego el postId");
            finish();
        }
        presenter = new PostPresenter(new PostDetailInteractorImpl());
        presenter.attachView(this);
        presenter.getPost(id);
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this,errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getPostSuccess(Post post) {
        idTextView.setText(String.valueOf(post.getId()));
        userIdTextView.setText(String.valueOf(post.getUserId()));
        titleTextView.setText(post.getTitle());
        bodyTextView.setText(post.getText());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.detachView();
    }
}

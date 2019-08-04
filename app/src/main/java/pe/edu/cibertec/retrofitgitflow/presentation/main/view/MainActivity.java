package pe.edu.cibertec.retrofitgitflow.presentation.main.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.MyApplication;
import pe.edu.cibertec.retrofitgitflow.R;
import pe.edu.cibertec.retrofitgitflow.base.BaseActivity;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;
import pe.edu.cibertec.retrofitgitflow.di.components.DaggerPresentationComponent;
import pe.edu.cibertec.retrofitgitflow.di.modules.PresentationModule;
import pe.edu.cibertec.retrofitgitflow.presentation.main.IMainContract;
import pe.edu.cibertec.retrofitgitflow.presentation.main.presenter.MainPresenter;
import pe.edu.cibertec.retrofitgitflow.presentation.post_detail.view.PostDetailActivity;

public class MainActivity extends BaseActivity
        implements IMainContract.IView {

    private TextView textViewResult;
    private RecyclerView recyclerViewPosts;
    private ProgressBar progressBarMain;
    private PostAdapter postAdapter;
    private List<Post> postList;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        presenter.attachView(this);
        textViewResult = findViewById(R.id.textViewResult);
        progressBarMain = findViewById(R.id.progressBarMain);
        recyclerViewPosts = findViewById(R.id.recyclerViewPosts);
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));
        this.postList = new ArrayList<>();
        postAdapter = new PostAdapter(postList);
        postAdapter.setOnItemClickListener(new PostClickListener() {
            @Override
            public void onClick(int position) {
                gotToDetailPost(postList.get(position).getId());
            }
        });
        recyclerViewPosts.setAdapter(postAdapter);
        presenter.getAllPost();
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPresentationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presentationModule(new PresentationModule())
                .build().inject(this);
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBarMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarMain.setVisibility(View.GONE);
    }

    @Override
    public void getAllPostSuccess(List<Post> postList) {
        this.postList.addAll(postList);
        postAdapter.notifyDataSetChanged();
    }

    @Override
    public void gotToDetailPost(int postId) {
        Intent intent = new Intent(this, PostDetailActivity.class);
        intent.putExtra("post_id", postId);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.detachView();
        super.onDetachedFromWindow();
    }
}

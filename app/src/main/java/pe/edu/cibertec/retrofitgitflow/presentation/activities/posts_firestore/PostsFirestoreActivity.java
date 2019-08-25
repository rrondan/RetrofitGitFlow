package pe.edu.cibertec.retrofitgitflow.presentation.activities.posts_firestore;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.R;
import pe.edu.cibertec.retrofitgitflow.base.BaseActivity;
import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;
import pe.edu.cibertec.retrofitgitflow.di.components.DaggerPresentationComponent;
import pe.edu.cibertec.retrofitgitflow.di.modules.PresentationModule;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.create_post_firestore.CreatePostFirestoreActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.post_detail_firestore.PostDetailFirestoreActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.adapters.NewPostAdapter;
import pe.edu.cibertec.retrofitgitflow.presentation.adapters.PostAdapter;
import pe.edu.cibertec.retrofitgitflow.presentation.utils.AdapterClickListener;

public class PostsFirestoreActivity extends BaseActivity implements IPostsFirestoreContract.IView{

    private TextView tvNoPosts;
    private FloatingActionButton fabAdd;
    private RecyclerView recyclerViewPosts;
    private ProgressBar progressBarMain;
    private NewPostAdapter newPostAdapter;
    private List<NewPost> newPostList;

    @Inject
    PostsFirestorePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_posts_firestore;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        presenter.attachView(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvNoPosts = findViewById(R.id.tv_no_posts);
        fabAdd = findViewById(R.id.fab_add);
        recyclerViewPosts = findViewById(R.id.recyclerViewPosts);
        progressBarMain = findViewById(R.id.progressBarMain);
        setAdapter();
        setListeners();
        presenter.getAllPost();
    }

    private void setAdapter(){
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));
        newPostList = new ArrayList<>();
        newPostAdapter = new NewPostAdapter(newPostList);
        newPostAdapter.setOnItemClickListener(new AdapterClickListener() {
            @Override
            public void onClick(int position) {
                gotToDetailPost(newPostList.get(position).getId());
            }
        });
        recyclerViewPosts.setAdapter(newPostAdapter);
    }

    private void setListeners(){
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CreatePostFirestoreActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
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
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_SHORT).show();
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
    public void showNoPost() {
        tvNoPosts.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoPost() {
        tvNoPosts.setVisibility(View.GONE);
    }

    @Override
    public void refreshRecyclerView(List<NewPost> postList) {
        this.newPostList.clear();
        this.newPostList.addAll(postList);
        newPostAdapter.notifyDataSetChanged();
    }

    @Override
    public void gotToDetailPost(String postUid) {
        Intent intent = new Intent(this, PostDetailFirestoreActivity.class);
        intent.putExtra("post_id", postUid);
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

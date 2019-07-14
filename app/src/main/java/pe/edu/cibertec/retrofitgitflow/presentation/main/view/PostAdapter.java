package pe.edu.cibertec.retrofitgitflow.presentation.main.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.R;
import pe.edu.cibertec.retrofitgitflow.data.entities.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> postList;
    private PostItemClickListener postItemClickListener;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    public void setOnItemClickListener(PostItemClickListener postItemClickListener){
        this.postItemClickListener = postItemClickListener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.textViewTitle.setText(post.getTitle());
        holder.textViewBody.setText(post.getText());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView textViewTitle;
        private TextView textViewBody;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewBody = itemView.findViewById(R.id.textViewBody);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }

        @Override
        public void onClick(View v) {
            postItemClickListener.onItemClick(getAdapterPosition());
        }
    }
}

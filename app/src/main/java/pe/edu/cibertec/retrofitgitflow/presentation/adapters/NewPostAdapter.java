package pe.edu.cibertec.retrofitgitflow.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.R;
import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;
import pe.edu.cibertec.retrofitgitflow.presentation.utils.AdapterClickListener;

public class NewPostAdapter extends RecyclerView.Adapter<NewPostAdapter.NewPostViewHolder>  {

    private List<NewPost> newPostList;
    private AdapterClickListener clickListener;

    public NewPostAdapter(List<NewPost> newPostList) {
        this.newPostList = newPostList;
    }

    public void setOnItemClickListener(AdapterClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NewPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_new_post, parent, false);
        return new NewPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewPostViewHolder holder, int position) {
        NewPost post = newPostList.get(position);
        if(post.getPathPhoto() != null){
            Glide.with(holder.itemView.getContext())
                .load(post.getPathPhoto())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.logo_cibertec))
                .into(holder.ivPhoto);
        } else {
            holder.ivPhoto.setVisibility(View.GONE);
        }
        holder.textViewTitle.setText(post.getTitle());
        holder.textViewBody.setText(post.getContent());
    }

    @Override
    public int getItemCount() {
        return newPostList.size();
    }

    public class NewPostViewHolder extends RecyclerView.ViewHolder{
        ImageView ivPhoto;
        TextView textViewTitle;
        TextView textViewBody;

        public NewPostViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewBody = itemView.findViewById(R.id.textViewBody);
            itemView.setOnClickListener(v -> clickListener.onClick(getAdapterPosition()));
        }
    }
}

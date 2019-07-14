package pe.edu.cibertec.retrofitgitflow;

import android.app.Activity;
import android.content.Intent;

import pe.edu.cibertec.retrofitgitflow.paid.PostDetailActivity;

public class TriggerClick {
    public final static void selectItem(int id, Activity activity){
        Intent intent = new Intent(activity, PostDetailActivity.class);
        intent.putExtra("post_id",id);
        activity.startActivity(intent);
    }
}

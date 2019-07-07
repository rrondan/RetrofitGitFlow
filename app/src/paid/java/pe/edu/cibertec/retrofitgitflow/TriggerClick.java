package pe.edu.cibertec.retrofitgitflow;

import android.app.Activity;
import android.content.Intent;

import pe.edu.cibertec.retrofitgitflow.paid.DetailPostActivity;

public class TriggerClick {
    public final static void selectItem(int id, Activity activity){
        Intent goDetailIntent = new Intent(activity, DetailPostActivity.class);
        goDetailIntent.putExtra("id", id);
        activity.startActivity(goDetailIntent);
    }
}

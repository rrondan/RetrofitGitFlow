package pe.edu.cibertec.retrofitgitflow;

import android.app.Activity;
import android.widget.Toast;

public class TriggerClick {
    public final static void selectItem(int id, Activity activity){
        Toast.makeText(activity, "Esta funcionalidad es de paga", Toast.LENGTH_SHORT).show();
    }
}

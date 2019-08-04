package pe.edu.cibertec.retrofitgitflow.data.entities;

import com.google.gson.annotations.SerializedName;

public class Comment {
    private int postId;
    private int id;
    @SerializedName("name")
    private String nombre;
    @SerializedName("email")
    private String correo;
    @SerializedName("body")
    private String comentario;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getComentario() {
        return comentario;
    }
}

package pe.edu.cibertec.retrofitgitflow.di.components;

import dagger.Component;
import pe.edu.cibertec.retrofitgitflow.di.modules.PresentationModule;
import pe.edu.cibertec.retrofitgitflow.di.scope.PerActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.create_post_firestore.CreatePostFirestoreActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.login.LoginActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.main.MainActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.menu.MenuActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.post_detail.PostDetailActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.post_detail_firestore.PostDetailFirestoreActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.posts_firestore.PostsFirestoreActivity;
import pe.edu.cibertec.retrofitgitflow.presentation.activities.register.RegisterActivity;

@PerActivity
@Component (modules = PresentationModule.class, dependencies = ApplicationComponent.class)
public interface PresentationComponent {
    void inject(MainActivity mainActivity);
    void inject(PostDetailActivity postDetailActivity);
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(PostsFirestoreActivity postsFirestoreActivity);
    void inject(CreatePostFirestoreActivity createPostFirestoreActivity);
    void inject(PostDetailFirestoreActivity postDetailFirestoreActivity);
    void inject(MenuActivity menuActivity);
}

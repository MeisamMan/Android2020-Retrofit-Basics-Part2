package org.meicode.retrofitbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllPosts();
            }
        });
    }

    private void getAllPosts() {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();

        PostEndPoint endPoint = retrofit.create(PostEndPoint.class);
//        int id = Integer.valueOf(editText.getText().toString());
//        Call<List<Post>> call = endPoint.getPosts();
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                ArrayList<Post> posts = (ArrayList<Post>) response.body();
//                for (Post p: posts) {
//                    textView.setText(textView.getText() + "\n\t" + p.getTitle());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

//        Call<Post> call = endPoint.getSinglePost(id);
//        call.enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                Post post = response.body();
//                textView.setText(post.getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

//        Call<List<Post>> call = endPoint.getPostsByUserId(id);
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                ArrayList<Post> posts = (ArrayList<Post>) response.body();
//                for (Post p: posts) {
//                    textView.setText(textView.getText().toString() + "\n\t\t" + p.getTitle());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

        Post post = new Post(102, "New Post", "The body of new Post");

        String token = "iuhsidfjbjhkjsdgf";

        Call<Post> call = endPoint.newPost(token, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    Post newPost = response.body();
                    textView.setText(newPost.getBody());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

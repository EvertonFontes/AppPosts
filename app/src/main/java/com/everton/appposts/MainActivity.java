package com.everton.appposts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private ArrayList<Post> listPost = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_post);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //listPost.add(new Post("teste","teste"));

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<ArrayList<Post>> call = api.getPosts();

        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                if(response.isSuccessful()){
                    listPost = new ArrayList<>(response.body());
                      /* se não tiver usando cardView inflado
                    for(Post post : listPost){
                        title.setText(post.getTitle());
                    }
                     */
                    postAdapter = new PostAdapter(listPost,MainActivity.this);
                    recyclerView.setAdapter(postAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Oops! Ocorreu um erro!!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}

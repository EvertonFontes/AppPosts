package com.everton.appposts;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("posts")
    Call<ArrayList<Post>> getPosts();
}

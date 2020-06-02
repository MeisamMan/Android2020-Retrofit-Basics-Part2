package org.meicode.retrofitbasics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostEndPoint {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getSinglePost(@Path("id") int id);

    @GET("posts")
    Call<List<Post>> getPostsByUserId(@Query("userId") int id);

    @POST("posts")
    Call<Post> newPost(@Header ("token") String token, @Body Post post);
}

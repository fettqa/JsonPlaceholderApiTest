package retrofit.adapters;

import retrofit.pojo.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import java.util.List;

public interface APIService {

    @GET("posts")
    public Call<List<Posts>> getPosts();

    @GET("comments")
    public Call<List<Comments>> getComments();

    @GET("albums")
    public Call<List<Albums>> getAlbums();

    @GET("photos")
    public Call<List<Photos>> getPhotos();

    @GET("todos")
    public Call<List<Todos>> getTodos();

    @GET("users")
    public Call<List<Users>> getUsers();

}

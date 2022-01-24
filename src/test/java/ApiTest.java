import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import retrofit.adapters.APIService;
import retrofit.pojo.Posts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ApiTest {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Test
    public void test(){
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Posts>> posts = apiService.getPosts();

        posts.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                Assertions.assertEquals(response.code(),200);
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable throwable) {

            }
        });
        try {
            System.out.println(posts.execute().body());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

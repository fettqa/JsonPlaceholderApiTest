package retrofit.builder;

import retrofit.adapters.APIService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String URL = "http://jsonplaceholder.typicode.com/";

    private Retrofit retrofit;

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIService getApiService(){
        return getRetrofitInstance().create(APIService.class);
    }


}

package com.st.vehicleregister.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://test.turbocare.app";

    private static String TAG = "Retrofit Response";

    public static Retrofit getRetrofitInstance() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.callTimeout(3000, TimeUnit.SECONDS);
        httpClient.readTimeout(3000, TimeUnit.SECONDS);
        httpClient.connectTimeout(3000, TimeUnit.SECONDS);
        httpClient.writeTimeout(3000, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request().newBuilder().addHeader("Accept", "application/json")
//                        .addHeader("Authorization", token)
                        .build();

                return chain.proceed(request);
            }
        });

        httpClient.addInterceptor(logging);
//        httpClient.addNetworkInterceptor(new StethoInterceptor());
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit;
    }
}

package com.st.vehicleregister.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Laxmi Kant Joshi on 02/07/2020.
 */
public interface ApiInterface {
    @GET("turbo/care/v1/makes")
    Call<String[]> getMakes(
            @Query(value = "class", encoded = true) String makeclass
    );

    @GET("turbo/care/v1/models")
    Call<String[]> getModels(
            @Query(value = "class", encoded = true) String makeclass,
            @Query(value = "make", encoded = true) String make
    );
}

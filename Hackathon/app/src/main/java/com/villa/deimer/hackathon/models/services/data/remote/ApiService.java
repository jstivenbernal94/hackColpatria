package com.villa.deimer.hackathon.models.services.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Field;
import com.google.gson.JsonObject;
import com.villa.deimer.hackathon.models.entities.Product;
import com.villa.deimer.hackathon.models.entities.User;

import java.util.List;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("/api/Account/Login")
    Call<User> login(
            @Field("usuario") String username,
            @Field("contraseña")String password,
            @Field("plataforma")String platform
    );

    @GET("/api/Product/ListProdUser")
    Call<List<Product>> getProducts(
            @Query("flag")int option
    );

}

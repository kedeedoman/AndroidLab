package org.mistu.android.androidlab.rest;

import org.mistu.android.androidlab.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FirebaseAPIService {
    @GET("/users.json")
    Call<List<User>> getAllUsers();

    @GET("/users.json")
    Call<User> getUserById(@Path("userId") Long id);

}


package org.mistu.android.androidlab.rest;

import org.mistu.android.androidlab.model.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FirebaseAPIService {
    @GET("/users.json")
    Call<Map<String, User>> getAllUsers();

    @GET("/users.json")
    Call<User> getUserById(@Path("userId") Long id);

}


package org.mistu.android.androidlab;
import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.mistu.android.androidlab.rest.FirebaseAPIService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class MyApplication extends MultiDexApplication {

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    private FirebaseAPIService firebaseAPIService;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        // Context
        Context context = this;

        // Network
        File cacheFile = new File(context.getCacheDir(), "network_cache");
        cacheFile.mkdirs();
        Cache cache = new Cache(cacheFile, 10*1000*1000); //10MB

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .build();

        // Gson
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        // Client
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://daggerlab-ab226.firebaseio.com")
                .build();

        firebaseAPIService = retrofit.create(FirebaseAPIService.class);

    }

    public FirebaseAPIService getFirebaseAPIService() {
        return firebaseAPIService;
    }
}

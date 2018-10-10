package org.mistu.android.androidlab.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.mistu.android.androidlab.MyApplicationScope;
import org.mistu.android.androidlab.rest.FirebaseAPIService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {NetworkModule.class})
public class FirebaseAPIServiceModule {

    @Provides
    @MyApplicationScope
    public FirebaseAPIService getFirebaseAPIService(Retrofit retrofit) {
        return retrofit.create(FirebaseAPIService.class);
    }

    @Provides
    @MyApplicationScope
    public Gson gson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    @MyApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://daggerlab-ab226.firebaseio.com")
                .build();
    }
}

package org.mistu.android.androidlab;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import org.mistu.android.androidlab.module.ContextModule;
import org.mistu.android.androidlab.rest.FirebaseAPIService;

import timber.log.Timber;

public class MyApplication extends MultiDexApplication {

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    private MyApplicationComponent myApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        myApplicationComponent = DaggerMyApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public MyApplicationComponent getMyApplicationComponent() {
        return myApplicationComponent;
    }

}

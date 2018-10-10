package org.mistu.android.androidlab.module;

import android.content.Context;

import org.mistu.android.androidlab.MyApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @MyApplicationScope
    public Context context() {
        return context;
    }
}

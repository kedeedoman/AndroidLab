package org.mistu.android.androidlab.screen;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    @MainActivityScope
    public UserListAdapter userListAdapter() {
        return new UserListAdapter();
    }

}

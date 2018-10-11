package org.mistu.android.androidlab.screen;

import org.mistu.android.androidlab.MyApplicationComponent;
import org.mistu.android.androidlab.rest.FirebaseAPIService;

import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = MyApplicationComponent.class)
@MainActivityScope
public interface MainActivityComponent {
    MainActivity injectMainActivity(MainActivity mainActivity);
}


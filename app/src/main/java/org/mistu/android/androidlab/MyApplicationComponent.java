package org.mistu.android.androidlab;

import org.mistu.android.androidlab.module.FirebaseAPIServiceModule;
import org.mistu.android.androidlab.rest.FirebaseAPIService;

import dagger.Component;

@MyApplicationScope
@Component(modules = {FirebaseAPIServiceModule.class})
public interface MyApplicationComponent {
    FirebaseAPIService getFirebaseAPIService();
}
